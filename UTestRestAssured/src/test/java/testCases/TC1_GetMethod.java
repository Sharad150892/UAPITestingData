package testCases;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
//import static io.restassured.RestAssured.given;

public class TC1_GetMethod {

	@Test
	public void testGet() {

		// Specify base URI
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		// Request Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET, "/users");

		JsonPath jsonpath = new JsonPath(response.asString());

		int size = jsonpath.getInt("size()");

		System.out.println("Number of User : " + size);

		for (int i = 0; i < size; i++) {

			String name = jsonpath.getString("[" + i + "].name");
			String phone = jsonpath.getString("[" + i + "].phone");
			String email = jsonpath.getString("[" + i + "].email");
			String street = jsonpath.getString("[" + i + "].address.street");

			if ((email.equals("Nathan@yesenia.net"))) {
				System.out.println("Name of User : " + name + ", " + email);
				Assert.assertEquals(email, "Nathan@yesenia.net");
			}

			if ((phone.equals("010-692-6593 x09125"))) {
				System.out.println("Name with phone  : " + name + ", " + phone);
				Assert.assertEquals(phone, "010-692-6593 x09125");
			}
			if ((street.equals("Hoeger Mall"))) {
				System.out.println("Name with Street  : " + name + ", " + street);
				Assert.assertEquals(street, "Hoeger Mall");
			}
		}

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
