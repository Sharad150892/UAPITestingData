package udemy;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import udemyFile.Payload;

public class SumValidation {
	
	@Test
	public void sumOfCources() {
		int sum = 0;
		JsonPath js = new JsonPath(Payload.coursePrice());
		
		int count = js.getInt("courses.size()");
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		for(int i=0; i<count; i++) {
			
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int amount = price*copies;
			System.out.println(amount);
			sum = sum+amount;
		}
		System.out.println("Total sum :"+sum);
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
	}

}
