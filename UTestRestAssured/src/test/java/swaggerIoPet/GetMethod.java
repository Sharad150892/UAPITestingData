package swaggerIoPet;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetMethod {
	
	
	@Test
	public void getTest() {
		RestAssured.baseURI = "https://reqres.in";
		
		RequestSpecification httpRequest = RestAssured.given().log().all();
		
		Response response = httpRequest.request(Method.GET,"/api/users");
		
		String responseBody = response.getBody().asString();
		
		System.out.println("Response Body : "+responseBody);
	}
	

}
