package restapilearning;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.libraryPayload;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryAPITest {
	
	@Test(dataProvider = "AddBookData")
	public void addBook(String isbn,String aisle) {
		baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type","application/json").log().all().body(libraryPayload.addBookPayload(isbn,aisle)).when().post("/Library/Addbook.php").then().log().all().statusCode(200).body("Msg",equalTo("successfully added")).extract().asString();
		
		JsonPath responseJSONPath = new JsonPath(response);
		String id = responseJSONPath.getString("ID");
		System.out.println("id is "+id);
		
	}
	//xyzqq123
	@DataProvider(name="AddBookData")
	public Object[][] addBookData() {
		return new Object[][] {{"abcv","12"},{"mars","23"},{"nikm","09"}};
	}

	

}
