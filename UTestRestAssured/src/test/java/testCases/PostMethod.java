package testCases;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostMethod {
	
	
	@Test
	public void testPost() {

		// Specify base URI
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		// Request Object
		RequestSpecification httprequest = RestAssured.given();
		
		HashMap data = new HashMap();
		data.put("name", "Sharad");
		data.put("username", "sharad.sahu");
		data.put("email", "sharad.sahu@xyz.com");
		
		httprequest.header("Content-Type","application/json");
		httprequest.body(data);
		
		Response response = httprequest.request(Method.PUT, "/users");
		
		String responBody = response.getBody().asString();
		System.out.println("Body : "+responBody);

	}
			
}






















