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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appspotName == null) ? 0 : appspotName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appspot other = (Appspot) obj;
		if (appspotName == null) {
			if (other.appspotName != null)
				return false;
		} else if (!appspotName.equals(other.appspotName))
			return false;
		return true;
	}    
    
}
