package br.ufrn.dimap.orchestrator.domain.application;

/**
 * Google Cloud Services available.
 * 
 * https://cloud.google.com/appengine/docs/standard/java/images/
 * https://console.cloud.google.com/apis/library
 * 
 * @author vitorgreati
 */
public enum GoogleCloudService {
	
	GCLOUD_DATASTORE("Datastore API"),
	GCLOUD_FIRESTORE("Firestore API"),
	GMAIL("Gmail API"),
	FIREBASE_RULES("Firebase rules API"),
	FIREBASE_DYNAMIC_LINKS("Firebase Dynamic Links API"),
	GCLOUD_STORAGE("Google Cloud Storage"),
	GCLOUD_SQL("Google Cloud SQL"),
	GCLOUD_STORAGE_JSON("Google Cloud Storage JSON API"),
	GOOGLE_DRIVE("Google Drive API"),
	STORAGE_TRANSFER("Storage Transfer API"),
	CONTACTS("Contacts API"),
	GOOGLE_PEOPLE("Google People API"),
	GOOGLE_PLUS("Google+ API"),
	GOOGLE_PLUS_DOMAINS("Google+ Domains API"),
	COMPUTE_ENGINE("Google Compute Engine"),
	DIRECTIONS("Direction API"),
	DISTANCE("Distance Matrix API"),
	GEOCODING("Geocoding API"),
	GEOLOCATION("Geolocation API"),
	PLACES("Places API"),
	STREET_VIEW("Street View API"),
	BIG_QUERY("Big Query API"),
	CLOUD_VISION("Cloud Vision API"),
	CLOUD_IOT("Google Cloud IoT API"),
	AUTHENTICATION("User authentication"),
	MEMCACHE("Memcache"),
	HTTP_REQUESTS("HTTP Requests"),
	IMAGES("Images API"),
	MULTITENANCY("Multitenancy API"),
	TRANSACTIONS("Transactions")
	;
	
	private final String description;

	private GoogleCloudService(String description) {
		this.description = description;
	}
	
	public String description() {
		return this.description;
	}
}
