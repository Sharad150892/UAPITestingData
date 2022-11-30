package reqrusTest;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class TC_DeleteTest {

	@Test
	public void testDelete() {

		// Specify base URI
		RestAssured.baseURI = "https://reqres.in";

		// Request Object
		RequestSpecification httprequest = RestAssured.given().header("Content-Type", "application/json");

		// Response Object
		Response response = httprequest.request(Method.DELETE, "/api/users/2");

		System.out.println("<-------------------------------------->");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Json Body : " + responseBody);

		System.out.println("<-------------------------------------->");

		// Status code validation
		int statuscode = response.getStatusCode();
		System.out.println("Status Code is : " + statuscode);
		Assert.assertEquals(statuscode, 204);

		// Status Line Validation
		String statusline = response.getStatusLine();
		System.out.println("Status Line is : " + statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 204 No Content");

		Headers allheaders = response.headers(); // capture all header from response

		for (Header header : allheaders) {
			System.out.println(header.getName() + "   " + header.getValue());

		}

		// Validate Response Time
		long responsetime = response.getTime();
		System.out.println("Response Time : " + responsetime);
		ValidatableResponse validateResponse = response.then();
		validateResponse.time(Matchers.lessThan(5000L));
	}

}
