package br.ufrn.dimap.orchestrator.domain.ranking;

import java.util.ArrayList;
import java.util.List;

public class Ranking {

    private List<RankedApplication> rankedApplications;

    public void addRankedApplication(int rankPosition, String appspot, String appName, String ownerName, double score, int usedGoogleServicesCount, int integrationCountAsClient, int integrationCountAsServer, Long integrationFiwareCount){
        rankedApplications.add(new RankedApplication(rankPosition, appspot, appName,ownerName,score,usedGoogleServicesCount,integrationCountAsClient,integrationCountAsServer, integrationFiwareCount));
    }

    public Ranking(List<RankedApplication> rankedApplications) {
        this.rankedApplications = rankedApplications;
    }
    
    public Ranking() {
        rankedApplications = new ArrayList<>();
    }

    public List<RankedApplication> getRankedApplications() {
        return rankedApplications;
    }

}
