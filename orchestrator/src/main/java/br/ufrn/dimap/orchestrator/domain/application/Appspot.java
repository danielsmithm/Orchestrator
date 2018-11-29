package br.ufrn.dimap.orchestrator.domain.application;

public class Appspot {

    private String appspotName;

    public Appspot(String appspotName) {
        this.appspotName = appspotName;
    }

    public static Appspot from(String appspotName) {
        return new Appspot(appspotName);
    }

    public String getAppspotName() {
        return appspotName;
    }
}
