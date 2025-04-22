package cucumberFramework.stepDefinition;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlacePOJO;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilis;

public class PlaceAPI extends Utilis{
	RequestSpecification request; Response response; String responseBody;
	TestDataBuild data = new TestDataBuild();
	
	
	@Given("Add Place Payload")
	public void add_Place_Payload() throws FileNotFoundException {
		
		
		baseURI	= "https://rahulshettyacademy.com";
		//basePath ="/maps/api/place/add/json";
		
		
		
	request = 	given().spec(requestSpecification()).body(data.addPlacePayload()).log().all();
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_Post_http_request(String resource,String method) {
		APIResources resources = APIResources.valueOf(resource);
		System.out.println("api resources "+resources +"get resources "+resources.getResource());
		if(method.equalsIgnoreCase("POST"))
			response = request.when().post(resources.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = request.when().get(resources.getResource());
		else
			response = request.when().delete(resources.getResource());
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer statCode) {
    ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(statCode).build();
		
		
    responseBody = response.then().spec(resSpec).extract().asString();
		System.out.println(responseBody);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	   
		
		JsonPath js = new JsonPath(responseBody);
		String actual = js.get(keyValue);
		Assert.assertEquals(actual, expectedValue);
	    
	}
}
