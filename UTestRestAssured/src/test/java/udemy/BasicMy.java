package udemy;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import udemyFile.Payload;

import static org.hamcrest.Matchers.*;

public class BasicMy {
	
	
	@Test
	public void AddPlace() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		RequestSpecification httpRequest = RestAssured.given().log().all();
		
		httpRequest.queryParam("key", "qaclick123").header("Content-Type","application/json");
		
		httpRequest.body(Payload.addPlace());
		
			Response response = httpRequest.request(Method.PUT,"/maps/api/place/add/json");
			
			response.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.41 (Ubuntu)");
			
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200);
			
			String resBody = response.getBody().asString();
			System.out.println("Response Body"+resBody);
			
			
			long restime = response.getTime();
			System.out.println("Response Time : "+restime);
			ValidatableResponse valRes = response.then().log().all();
			
		
	}

}
