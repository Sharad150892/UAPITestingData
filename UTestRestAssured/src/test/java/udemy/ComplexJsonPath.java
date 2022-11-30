package udemy;

import io.restassured.path.json.JsonPath;
import udemyFile.Payload;

public class ComplexJsonPath {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(Payload.coursePrice());

		// Print No of courses returned by API
		System.out.println("Print No of courses returned by API");
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// Print Purchase Amount
		
		System.out.println("Print Purchase Amount");
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);

		// Print Title of the first course
		System.out.println("Print Title of the first course");
		String titleOfFirstCourse = js.get("courses[0].title");
		System.out.println(titleOfFirstCourse);

		// Print All course titles and their respective Prices
		System.out.println("Print All course titles and their respective Prices");
		for (int i = 0; i < count; i++) {
			String courseTitle = js.getString("courses[" + i + "].title");

			int coursePrice = js.getInt("courses[" + i + "].price");
			System.out.println(courseTitle);
			System.out.println(coursePrice);
		}
		
		//Print no of copies sold by RPA Course
		
		System.out.println("Print no of copies sold by RPA Course");
		for (int i = 0; i < count; i++) {
			String courseTitle = js.getString("courses[" + i + "].title");
			if(courseTitle.equalsIgnoreCase("RPA")) {
				int noOfCopies = js.getInt("courses["+i+"].copies");
				System.out.println(noOfCopies);
				break;
			}
		}

	}

}
