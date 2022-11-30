package udemy1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.PayLoad;
import files.ReusableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().log().all().header("Content-Type","application/json").
							body(PayLoad.addBookPayLoad(isbn,aisle)).
							when().post("Library/Addbook.php").
							then().log().all().assertThat().statusCode(200).
							extract().response().asString();
		
		JsonPath js = ReusableMethod.rawToJson(response);
		
		String id = js.get("ID");
		System.out.println("ID = "+id);
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		
		//array is collection of Element
		// multidimensional array is collection of array
		
		return new Object[][] {{"fdgd","2253"},{"ddgs","44564"},{"greg","4236"}};
				
	}

}





























