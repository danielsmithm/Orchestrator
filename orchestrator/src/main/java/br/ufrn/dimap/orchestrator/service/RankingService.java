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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
    public Ranking generateRanking(){
    	    	
    	Ranking ranking = new Ranking();
    	
    	List<Token> allTokens = tokenService.listAllTokens();
    	
    	List<Application> allApplications = applicationService.findAllApplications();
    	
    	Map<String, RankedApplication> rankedApps = new HashMap<>();
    	
    	for (Application app : allApplications) {
    		RankedApplication rankedApplication = new RankedApplication();
    		
    		
    		rankedApplication.setAppName(app.getAppName());
    		rankedApplication.setAppspot(app.getAppspot().getAppspotName());
    		rankedApps.put(app.getAppspot().getAppspotName(), rankedApplication);
    	}
    	
        return ranking;
    }

}
