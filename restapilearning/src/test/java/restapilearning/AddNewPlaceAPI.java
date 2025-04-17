package restapilearning;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class AddNewPlaceAPI {
	
	public static void main(String[] args) {
		
		//new String(Files.readAllBytes(Paths.get(fileLocation)))
		
		
		
		baseURI	= "https://rahulshettyacademy.com";
		//basePath ="/maps/api/place/add/json";
		
		String response = given().header("Content-Type", "application/json").queryParam("key", "qaclick123").body(payload.AddPlaceBody()).log().all()
		.when().post("maps/api/place/add/json").then().log().all().body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String place_id = js.getString("place_id");
		System.out.println("place_id "+place_id);
		
		//update the address for the place_id
		
		String newAddress = "70 Summer walk, Raleigh";
		
		given().log().all().queryParam("key", "qaclick123").body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
		//get the place_id
		System.out.println("Get place \n");
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id).when().get("maps/api/place/get/json").then().log().all().extract().response().asString();
		
		JsonPath js1 = new JsonPath(getPlaceResponse);
		String responseAddress = js1.getString("address");
		
		Assert.assertEquals(responseAddress, newAddress,"Actual and Expected one are not equal");
		
	}

}
