package reqrusTest;

import java.util.HashMap;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class TC_PutTest {
	
	
	@Test
	public void testPut() {
		
		RestAssured.baseURI ="https://reqres.in";
		
		RequestSpecification httprequest = RestAssured.given();
		
		HashMap data = new HashMap();
		data.put("name", "morpheus");
		data.put("job", "zion resident");
		
		
		httprequest.header("Content-Type","application/json");
		
		httprequest.body(data);
		
		Response response = httprequest.request(Method.PUT,"/api/users/2");
		
		
		System.out.println("<-------------------------------------->");
		String responBody = response.getBody().asString();
		System.out.println("Response Body : "+responBody);
		System.out.println("<-------------------------------------->");
		
		int statusCode = response.getStatusCode();
		System.out.println("Status Code : "+statusCode);
//		Assert.assertEquals(statusCode, 200);
		
		String statusLine = response.getStatusLine();
		System.out.println("Status line : "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		long Rtime = response.getTime();
		System.out.println("Response Time : "+Rtime);
		ValidatableResponse vr = response.then();
		vr.time(Matchers.lessThan(5000L));
		
		
		
	}
	

}
