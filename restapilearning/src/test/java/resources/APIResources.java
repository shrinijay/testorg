package resources;

public enum APIResources {
	
	AddPlaceAPI("maps/api/place/add/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	DeletePlaceAPI("maps/api/place/delete/json");
	
	private String rsc;
	
	APIResources(String rsc){
		this.rsc = rsc;
	}
	
	public String getResource() {
		return rsc;
	}

}
