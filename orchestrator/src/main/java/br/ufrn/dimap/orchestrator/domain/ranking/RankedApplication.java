package br.ufrn.dimap.orchestrator.domain.ranking;

public class RankedApplication {

    private int rankPosition;
    private String appName;
    private String ownerName;
    private double score;
    private int usedGoogleServicesCount;
    private int integrationCountAsClient;
    private int integrationCountAsServer;

    public RankedApplication() {
    }

    public RankedApplication(int rankPosition, String appName, String ownerName, double score, int usedGoogleServicesCount, int integrationCountAsClient, int integrationCountAsServer) {
        this.rankPosition = rankPosition;
        this.appName = appName;
        this.ownerName = ownerName;
        this.score = score;
        this.usedGoogleServicesCount = usedGoogleServicesCount;
        this.integrationCountAsClient = integrationCountAsClient;
        this.integrationCountAsServer = integrationCountAsServer;
    }

    public int getRankPosition() {
        return rankPosition;
    }

    public String getAppName() {
        return appName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getScore() {
        return score;
    }

    public int getUsedGoogleServicesCount() {
        return usedGoogleServicesCount;
    }

    public int getIntegrationCountAsClient() {
        return integrationCountAsClient;
    }

    public int getIntegrationCountAsServer() {
        return integrationCountAsServer;
    }
}
