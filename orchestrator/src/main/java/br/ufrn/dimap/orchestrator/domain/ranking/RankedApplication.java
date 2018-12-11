package br.ufrn.dimap.orchestrator.domain.ranking;

public class RankedApplication {

    private int rankPosition;
    private String appspot;
    private String appName;
    private String ownerName;
    private Double score;
    private int usedGoogleServicesCount;
    private int integrationCountAsClient;
    private int integrationCountAsServer;
    private Long integrationFiwareCount;

    public RankedApplication() {
    }

    public RankedApplication(int rankPosition, String appspot, String appName, String ownerName, double score, int usedGoogleServicesCount, int integrationCountAsClient, int integrationCountAsServer, Long integrationFiwareCount) {
        this.setRankPosition(rankPosition);
        this.setAppspot(appspot);
        this.setAppName(appName);
        this.ownerName = ownerName;
        this.setScore(score);
        this.setUsedGoogleServicesCount(usedGoogleServicesCount);
        this.setIntegrationCountAsClient(integrationCountAsClient);
        this.setIntegrationCountAsServer(integrationCountAsServer);
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

    public Double getScore() {
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

	public Long getIntegrationFiwareCount() {
		return integrationFiwareCount;
	}

	public void setIntegrationFiwareCount(Long integrationFiwareCount) {
		this.integrationFiwareCount = integrationFiwareCount;
	}

	public void setUsedGoogleServicesCount(int usedGoogleServicesCount) {
		this.usedGoogleServicesCount = usedGoogleServicesCount;
	}

	public void setIntegrationCountAsClient(int integrationCountAsClient) {
		this.integrationCountAsClient = integrationCountAsClient;
	}

	public void setIntegrationCountAsServer(int integrationCountAsServer) {
		this.integrationCountAsServer = integrationCountAsServer;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public void setRankPosition(int rankPosition) {
		this.rankPosition = rankPosition;
	}
}
