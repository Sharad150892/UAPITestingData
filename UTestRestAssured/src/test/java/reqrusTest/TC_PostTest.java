package reqrusTest;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class TC_PostTest {
	
	@Test
	public void testPost() {

		// Specify base URI
		RestAssured.baseURI = "https://reqres.in";

		// Request Object
		RequestSpecification httprequest = RestAssured.given();

//		HashMap data = new HashMap();
//		data.put("name", "morpheus");
//		data.put("job", "leader");
		
		HashMap<String, String> data = new HashMap();
		data.put("name", "morpheus");
		data.put("job", "leader");
		

		
		httprequest.header("Content-Type","application/json");
		httprequest.body(data);
		
		// Response Object
		Response response = httprequest.request(Method.POST, "/api/users").andReturn();

//		JsonPath jsonpath = new JsonPath(response.asString());
//
//		int size = jsonpath.getInt("size()");
//
//		System.out.println("Number of User : " + size);

		System.out.println("<-------------------------------------->");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body : " + responseBody);

		System.out.println("<-------------------------------------->");

		// Status code validation
		int statuscode = response.getStatusCode();
		System.out.println("Status Code is : " + statuscode);
		Assert.assertEquals(statuscode, 201);

		// Status Line Validation
		String statusline = response.getStatusLine();
		System.out.println("Status Line is : " + statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 201 Created");

		// Validate Response Time
		long responsetime = response.getTime();
		System.out.println("Response Time : " + responsetime);
		ValidatableResponse validateResponse = response.then();
		validateResponse.time(Matchers.lessThan(5000L));
	}

}
