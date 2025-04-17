package restapilearning;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.LinkedList;

import org.testng.Assert;

import files.payload;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlacePOJO;
import pojo.Location;

public class AddNewPlaceAPIWithPOJO {
	
	public static void main(String[] args) {
		
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
		
		
		
		
		baseURI	= "https://rahulshettyacademy.com";
		//basePath ="/maps/api/place/add/json";
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
		
	RequestSpecification request = 	given().spec(req).body(ap).log().all();
		
		Response rs = request.when().post("maps/api/place/add/json");
		
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectBody("scope", equalTo("APP")).expectStatusCode(200).build();
		
		
		String rr = rs.then().spec(resSpec).extract().asString();
		System.out.println("response "+rr);
		
		
		
		
	}

}
