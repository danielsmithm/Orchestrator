package br.ufrn.dimap.orchestrator.service;

import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.ranking.RankedApplication;
import br.ufrn.dimap.orchestrator.domain.ranking.Ranking;
import br.ufrn.dimap.orchestrator.domain.token.Token;
import br.ufrn.dimap.orchestrator.domain.token.TokenValidatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RankingService {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private ApplicationService applicationService;
	
	public RankingService(TokenService tokenService, ApplicationService applicationService) {
		this.tokenService = tokenService;
		this.applicationService = applicationService;
	}
	
	private final Double BASE_SCORE = 2.0;
	private final Double MAX_SCORE = 5.0;

	
    public Ranking generateRanking(Date initialDate){

    	List<Token> allTokens = tokenService.listAllTokens();

		if(initialDate != null && allTokens != null && !allTokens.isEmpty()){
			allTokens = allTokens.stream()
					.filter(token -> token.getValidationDate() != null)
					.filter(token -> token.getValidationDate().after(initialDate))
					.collect(Collectors.toList());
		}

    	List<Application> allApplications = applicationService.findAllApplications();
    	
    	Map<String, RankedApplication> rankedApps = new HashMap<>();
    	
    	Map<String, Set<String>> asServer = new HashMap<>();
    	Map<String, Set<String>> asClient = new HashMap<>();

    	// Compute as service and as client
    	for (Token token : allTokens) {
    		
    		// Allow only validated tokens
    		if (token.getValidationDate() == null)
    			continue;

    		String appspotServer = token.getServerAppspot().getAppspotName();
    		String appspotClient = token.getClientAppspot().getAppspotName();
    		
    		if (!asClient.containsKey(appspotClient)) {
    			asClient.put(appspotClient, new HashSet<>());
    		}
    		
    		if (!asServer.containsKey(appspotServer)) {
    			asServer.put(appspotServer, new HashSet<>());
    		}

    		asServer.get(appspotServer).add(appspotClient);
    		asClient.get(appspotClient).add(appspotServer);
    	}
    	
    	Double maxScore = 0.0;
    	
    	// Generate ranked application
    	for (Application app : allApplications) {
    		RankedApplication rankedApplication = new RankedApplication();
    		
    		String appspot = app.getAppspot().getAppspotName();
    		
    		if (asClient.containsKey(appspot))
    			rankedApplication.setIntegrationCountAsClient(asClient.get(appspot).size());
    		else
    			rankedApplication.setIntegrationCountAsClient(0);
    		
    		if (asServer.containsKey(appspot))
    			rankedApplication.setIntegrationCountAsServer(asServer.get(appspot).size());
    		else
    			rankedApplication.setIntegrationCountAsServer(0);
    		
    		rankedApplication.setOwnerName(app.getOwnerName());
    		rankedApplication.setUsedGoogleServicesCount(app.getGoogleServiceUse().size());
    		rankedApplication.setIntegrationFiwareCount(app.getFiwareUsesCount());
    		rankedApplication.setAppName(app.getAppName());
    		rankedApplication.setAppspot(app.getAppspot().getAppspotName());
    		
    		Double score = computeScore(rankedApplication);
    		rankedApplication.setScore(computeScore(rankedApplication));
    		
    		//set max score
    		maxScore = Math.max(score, maxScore);
    		
    		rankedApps.put(app.getAppspot().getAppspotName(), rankedApplication);
    	}    	
    	
    	// Rescale values
    	for (RankedApplication rankedApp : rankedApps.values()) {
    		Double score = rankedApp.getScore();
    		rankedApp.setScore(rescale(score, BASE_SCORE, maxScore, BASE_SCORE, MAX_SCORE));
    	}
    	
    	// List of ranked apps
    	List<RankedApplication> rankedAppsList = new ArrayList<>();
    	rankedAppsList.addAll(rankedApps.values());
    	rankedAppsList.sort((o1,o2) -> o2.getScore().compareTo(o1.getScore()));
    	
    	// Fill position attribute
    	for (int i = 0; i < rankedAppsList.size(); ++i) {
    		rankedAppsList.get(i).setRankPosition(i+1);
    	}
    	
    	Ranking ranking = new Ranking(rankedAppsList);

        return ranking;
    }
    
    private Double rescale(Double v, Double oldMin, Double oldMax, Double newMin, Double newMax) {
    	
    	Double newValue = ((newMax-newMin)/(oldMax-oldMin)) * (v - oldMax) + newMax;
    	
    	return newValue;
    }
    
    private Double computeScore(RankedApplication rankedApp) {
    	
    	int googleServices = rankedApp.getUsedGoogleServicesCount();
    	Long integrationFiware = rankedApp.getIntegrationFiwareCount();
    	int asClient = rankedApp.getIntegrationCountAsClient();
    	int asServer = rankedApp.getIntegrationCountAsServer();
    	
    	double wGS = 0.4, wFiware = 0.1, wAsServer=0.3, wAsClient=0.2;
    	
    	return BASE_SCORE + wGS*googleServices + wFiware*integrationFiware + wAsClient*asClient
    			+ wAsServer*asServer;
    }

}
