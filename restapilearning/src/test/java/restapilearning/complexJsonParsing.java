package restapilearning;

import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class complexJsonParsing {
	
   public static void main(String[] args) {
	
	   JsonPath js = new JsonPath(payload.courseDetails());
	   System.out.println("Courses count my way "+js.getList("courses").size());
	   System.out.println("Courses count rs way "+js.getInt("courses.size()"));
	   int purchaseAmnt = js.getInt("dashboard.purchaseAmount");
	   System.out.println("purchaseAmnt "+purchaseAmnt);
	   System.out.println("Title of the first course "+js.getString("courses[0].title"));
	   System.out.println("js.getList "+js.getList("courses"));
	   int sumOfIndPrice = 0;
	   for(int i=0;i<js.getInt("courses.size()");i++) {
		   String courseTitle = js.get("courses["+i+"].title");
		   int coursePrice = js.get("courses["+i+"].price");
		   int courseCopies = js.getInt("courses["+i+"].copies");
		   sumOfIndPrice += (coursePrice*courseCopies);
		   System.out.println("courseTitle "+courseTitle);
		   System.out.println("coursePrice "+coursePrice);
		   if(courseTitle.equalsIgnoreCase("Cypress")) {
			   System.out.println("Copies "+js.getString("courses["+i+"].copies"));
			   
		   }
	   }
	   System.out.println("Sum of Ind price "+sumOfIndPrice);
	   Assert.assertEquals(purchaseAmnt, sumOfIndPrice);
	   
}

}
