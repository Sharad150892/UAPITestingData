package reqrusTest;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class TC_Authorization {

	@Test
	public void authorization() {

		RestAssured.baseURI = "https://postman-echo.com/";
		
//		PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
//		authscheme.setUserName("postman");
//		authscheme.setPassword("password");
//		RestAssured.authentication = authscheme;

		RequestSpecification httprequest = RestAssured.given().auth().basic("postman","password");

		Response response = httprequest.request(Method.GET, "/basic-auth");

		// Print response body in console
		String responseBody = response.getBody().asString();
		System.out.println("response Body is : " + responseBody);

		// Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code : " + statusCode);
		Assert.assertEquals(statusCode, 200);

		long responsetime = response.getTime();
		System.out.println("Response Time : " + responsetime);
		ValidatableResponse validateResponse = response.then();
		validateResponse.statusCode(200);
		validateResponse.time(Matchers.lessThan(5000L));

	}

}
