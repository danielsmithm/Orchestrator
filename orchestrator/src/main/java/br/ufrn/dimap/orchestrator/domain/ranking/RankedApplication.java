package br.ufrn.dimap.orchestrator.domain.ranking;

public class RankedApplication {

    private int rankPosition;
    private String appspot;
    private String appName;
    private String ownerName;
    private double score;
    private int usedGoogleServicesCount;
    private int integrationCountAsClient;
    private int integrationCountAsServer;
    private int integrationFiwareCount;

    public RankedApplication() {
    }

    public RankedApplication(int rankPosition, String appspot, String appName, String ownerName, double score, int usedGoogleServicesCount, int integrationCountAsClient, int integrationCountAsServer, int integrationFiwareCount) {
        this.rankPosition = rankPosition;
        this.setAppspot(appspot);
        this.setAppName(appName);
        this.ownerName = ownerName;
        this.score = score;
        this.usedGoogleServicesCount = usedGoogleServicesCount;
        this.integrationCountAsClient = integrationCountAsClient;
        this.integrationCountAsServer = integrationCountAsServer;
        this.integrationFiwareCount = integrationFiwareCount;
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

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppspot() {
		return appspot;
	}

	public void setAppspot(String appspot) {
		this.appspot = appspot;
	}

	public int getIntegrationFiwareCount() {
		return integrationFiwareCount;
	}

	public void setIntegrationFiwareCount(int integrationFiwareCount) {
		this.integrationFiwareCount = integrationFiwareCount;
	}
}
