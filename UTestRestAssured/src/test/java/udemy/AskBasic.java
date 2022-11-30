package udemy;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import udemyFile.Payload;

import static io.restassured.RestAssured.*;

public class AskBasic {
	
	
	@Test
	public void post() {
		
		RestAssured.baseURI = "https://reqres.in";
		
		String response  = given().log().all().queryParam("id","2").queryParam("name","abc").
							header("Conyent-Type","application/json").
							body(Payload.nameDetails()).when().post("/api/users").then().log().all().assertThat().
							statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.getString("id");
		System.out.println("ID = "+id);
		
	}

}
