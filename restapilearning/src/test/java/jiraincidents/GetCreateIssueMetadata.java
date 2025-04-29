package jiraincidents;

import static io.restassured.RestAssured.*;

public class GetCreateIssueMetadata {
	
	public static void main(String[] args) {
		
		baseURI = "https://shrinijay.atlassian.net";
		


		given().auth().preemptive().basic("srvs1994@gmail.com", "ATATT3xFfGF0H5pcxqqc0B3ZZWP85qaASX2mYBzXa-Dju5dhJZJ9-AyWTPcjyMc29Rax30hWDV8Rn2MV_dg756CF6hpU4I-6VnzP3znsV8CCTlYjhNu5sxH7TqzC8VwachjNv2JQhREcFxZ1DnEIGeTgAGJPRKtNHGTPbG7hURzMiPD-Q8bB7y8=57146E7C").header("Accept","application/json").log().all().when().get("rest/api/3/issue/createmeta").then().log().all();
		
		//create an issue
		given().auth().basic("srvs1994@gmail.com", "ATATT3xFfGF0H5pcxqqc0B3ZZWP85qaASX2mYBzXa-Dju5dhJZJ9-AyWTPcjyMc29Rax30hWDV8Rn2MV_dg756CF6hpU4I-6VnzP3znsV8CCTlYjhNu5sxH7TqzC8VwachjNv2JQhREcFxZ1DnEIGeTgAGJPRKtNHGTPbG7hURzMiPD-Q8bB7y8=57146E7C").header("Accept", "application/json").header("Content-Type", "application/json").body("{\r\n"
				+ "\"fields\":{\n"
				+ "  \"summary\": \"New issue from Rest Assured Code\",\n"
				+ "  \"issuetype\": {\n"
				+ "      \"id\" : \"\"10005\"\n"
				+ "  },\n"
				+ "  \"project\" : {\n"
				+ "    \"id\" : \"10000\"\n"
				+ "  }\n"
				+ "}\n"
				+ "}").log().all().when().post("/rest/api/3/issue").then().log().all();
	
		
	}

}
