package udemy;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import udemyFile.Payload;
import udemyFile.ReusableMethod;

public class Basic {

	@Test
	public void endToEndMethod() {
		// Validate add place
		// given - all input details

		// when - submit API, resource http method

		// Then - validate the response
		
		//add place post
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.addPlace()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)").extract().response()
				.asString();

		System.out.println("Response Body : " + response);

		JsonPath js = new JsonPath(response);
		String placeID = js.getString("place_id");
		System.out.println("Place ID = "+placeID);

		// update place put
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
		
		//delete place
		given().log().all().header("Content-Type","application/json").body(placeID).when().delete("/maps/api/place/delete/json").
		 then().assertThat().statusCode(200);
	}

}
