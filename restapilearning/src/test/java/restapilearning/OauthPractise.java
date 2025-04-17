package restapilearning;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import pojo.Course;

import static io.restassured.RestAssured.*;

public class OauthPractise {

	@Test()
	public void oAuthTest() {
		
		String response = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials").formParam("scope", "trust").log().all().when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").then().log().all().extract().asString();
		System.out.println("response "+response);
		JsonPath js = new JsonPath(response);
		String access_token = js.getString("access_token");
		
		given().queryParam("access_token", access_token).log().all().when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").then().log().all();
		
		
	Course gc = given().queryParam("access_token", access_token).log().all().when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").then().extract().as(Course.class);
	
	System.out.println("gc "+gc.getCourses().getApi().get(0));
	
	System.out.println("gc size "+gc.getCourses().getWebAutomation().size());
	for(int i=0;i<gc.getCourses().getWebAutomation().size();i++) {
		System.out.println("each courses "+gc.getCourses().getWebAutomation().get(i).getCourseTitle());
		System.out.println("each courses price "+gc.getCourses().getWebAutomation().get(i).getPrice());
	}
		
		
	}
	
	
}
