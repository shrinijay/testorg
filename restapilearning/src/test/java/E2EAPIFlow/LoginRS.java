package E2EAPIFlow;

import org.testng.Assert;
import org.testng.annotations.Test;

import E2EAPIFlow.POJOFiles.Login;
import E2EAPIFlow.POJOFiles.OrderDetails;
import E2EAPIFlow.POJOFiles.OrderResponse;
import E2EAPIFlow.POJOFiles.Orders;
import E2EAPIFlow.POJOFiles.ViewOrderResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static io.restassured.RestAssured.*;


public class LoginRS {
	
	String token_id = "", user_id ="",product_id="",order_id="";
	
	@Test(priority=1)
	public void loginToApplication() {
		
		
		
		RequestSpecification loginSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		Login loginDetails = new Login();
		loginDetails.setUserEmail("abcxyz987@gmail.com");
		loginDetails.setUserPassword("Abcxyz@1");
		
		RequestSpecification reqLogin=given().spec(loginSpec).body(loginDetails).log().all();
		
		String response = reqLogin.when().post("api/ecom/auth/login").then().log().all().assertThat().statusCode(200).body("message",equalTo("Login Successfully")).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		token_id = js.getString("token");
		user_id = js.getString("userId");
		
		System.out.println("Token id is "+token_id);
		System.out.println("User id is "+user_id);
		}
	
	@Test(priority=2)
	public void createProduct() {
		RequestSpecification reqSpecs =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",token_id).build();
		
		RequestSpecification headers = given().spec(reqSpecs).param("productName", "3 realme").param("productAddedBy", user_id)
		.param("productCategory", "gadgets").param("productSubCategory", "mobile").param("productPrice","11200")
		.param("productDescription", "realme pro 11").param("productFor","General")
		.multiPart("productImage",new File("C:\\Users\\2394605\\Downloads\\mobile_phone_1.jpg"));
		
		product_id = headers.when().post("api/ecom/product/add-product").then().log().all().assertThat().statusCode(201).body("message", containsString("Product Added Successfully")).extract().jsonPath().getString("productId");
		
		System.out.println("ProductID from createProduct "+product_id);
		
}
	@Test(priority=3)
	public void createOrder() {
		
		RequestSpecification req =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",token_id).setContentType(ContentType.JSON).build();
		
		Orders order = new Orders();
		order.setCountry("India");
		order.setProductOrderedId(product_id);
		
		List<Orders> ordersList = new LinkedList();
		ordersList.add(order);
		
		OrderDetails ordDetails = new OrderDetails();
		ordDetails.setOrders(ordersList);
		
		RequestSpecification coReq = given().spec(req).log().all().body(ordDetails);
		
		OrderResponse or = coReq.when().post("api/ecom/order/create-order").then().log().all().statusCode(201).body("message",equalTo("Order Placed Successfully")).extract().response().as(OrderResponse.class);
		
		order_id = or.getOrders().get(0);
		
		System.out.println("or response order id "+order_id);
		
	}

@Test(priority = 4)
public void viewOrderdetails() {
	
	RequestSpecification rqSpcs = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com"
			).addHeader("authorization",token_id).setContentType(ContentType.JSON).addQueryParam("id",order_id).build();
	ViewOrderResponse vor = given().spec(rqSpcs).log().all().when().get("api/ecom/order/get-orders-details").then().log().all().extract().as(ViewOrderResponse.class);
	
	System.out.println("id is "+vor.getData().get_id());
	Assert.assertEquals(order_id, vor.getData().get_id());
	
	
	
	
}

@Test(priority = 5)
public void deleteProduct() {
	RequestSpecification rqSpcsDelete = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com"
			).addHeader("authorization",token_id).build();
	
	String res = given().spec(rqSpcsDelete).pathParam("productId", product_id).log().all().when().delete("api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
	
	System.out.println("res is "+res);
}

}
