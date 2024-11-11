package resources;

public enum  APIResource {
	
	GetPlaceAPI("/maps/api/place/get/json"),
	AddPlaceAPI("/maps/api/place/add/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");

	private String resource;
	APIResource(String resource) {
		this.resource =resource;	
	}
	
	public String getResouce() {
		return resource;
	}
}
