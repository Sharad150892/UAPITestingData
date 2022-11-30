package reqrusTest;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class TC_GetTest {
	
	@Test
	public void testGet() {

		// Specify base URI
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = "/api";

		// Request Object
		RequestSpecification httprequest = RestAssured.given();

		httprequest.header("Content-Type","application/json").queryParam("page","2");
		
		// Response Object
		Response response = httprequest.request(Method.GET, "/users/");

//		JsonPath jsonpath = new JsonPath(response.asString());
//
//		int size = jsonpath.getInt("size()");
//
//		System.out.println("Number of User : " + size);

		System.out.println("<-------------------------------------->");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Json Body : " + responseBody);

		System.out.println("<-------------------------------------->");

		// Status code validation
		int statuscode = response.getStatusCode();
		System.out.println("Status Code is : " + statuscode);
		Assert.assertEquals(statuscode, 200);

		// Status Line Validation
		String statusline = response.getStatusLine();
		System.out.println("Status Line is : " + statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");

		// Validate Response Time
		long responsetime = response.getTime();
		System.out.println("Response Time : " + responsetime);
		ValidatableResponse validateResponse = response.then();
		validateResponse.time(Matchers.lessThan(5000L));
	}

}
