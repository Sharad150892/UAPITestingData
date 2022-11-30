package udemy1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.Assert;
import org.testng.annotations.Test;
import files.ReusableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJsonFile {
	
	@Test
	public void addPlace() throws IOException {
		// Validate add place
		// given - all input details

		// when - submit API, resource http method

		// Then - validate the response
		
		// content of the file to String -> content of file can convert into Byte  -> Byte data to String

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String (Files.readAllBytes(Paths.get("D:\\Software Testing\\API Testing\\Udemy\\Payload.json"))))
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)").extract().response()
				.asString();

		System.out.println("Response Body : " + response);

		JsonPath js = new JsonPath(response);
		String placeID = js.getString("place_id");
		System.out.println("Place ID = "+placeID);

		// update place
		String newAddress = "Shrishti village, Mangalur Road, Bengaluru ";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeID + "\",\r\n" + "\"address\":\""+newAddress+"\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Get place
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
				.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response()
				.asString();
		
		System.out.println("Get Method = "+getPlaceResponse);

		JsonPath js1 = ReusableMethod.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
	}
}
