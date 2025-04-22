package resources;

import java.util.LinkedList;

import pojo.AddPlacePOJO;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlacePOJO addPlacePayload() {
	
	AddPlacePOJO ap = new AddPlacePOJO();
	ap.setAccuracy(50);
	ap.setName("Frontline house 1");
	ap.setPhone_number("(+91) 983 893 3937");
	ap.setAddress("29, side layout, san jose 08");
	LinkedList<String> ll= new LinkedList();
	ll.add("shoe park");
	ll.add("shop");
	ap.setTypes(ll);
	ap.setWebsite("http://google.com");
	ap.setLanguage("French-IN");
	
	Location loc = new Location();
	loc.setLat(-38.383494);
	loc.setLng(33.427362);
	
	ap.setLocation(loc);
	return ap;

}
}
