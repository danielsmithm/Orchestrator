package br.ufrn.dimap.orchestrator.domain.ranking;

import java.util.ArrayList;
import java.util.List;

public class Ranking {

    private List<RankedApplication> rankedApplications;

    public void addRankedApplication(int rankPosition, String appName, String ownerName, double score, int usedGoogleServicesCount, int integrationCountAsClient, int integrationCountAsServer){
        rankedApplications.add(new RankedApplication(rankPosition,appName,ownerName,score,usedGoogleServicesCount,integrationCountAsClient,integrationCountAsServer));
    }

    public Ranking() {
        rankedApplications = new ArrayList<>();
    }

    public List<RankedApplication> getRankedApplications() {
        return rankedApplications;
    }

}
