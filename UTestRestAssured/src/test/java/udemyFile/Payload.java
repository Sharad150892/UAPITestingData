package udemyFile;

public class Payload {
	
	public static String addPlace() {
		
		
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Sharad Sahu\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"Jhansi Rani Square, Bangalore\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
	}
	
	public static String coursePrice() {
		
		return "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 1162,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Appium\",\r\n"
				+ "\r\n"
				+ "\"price\": 36,\r\n"
				+ "\r\n"
				+ "\"copies\": 7\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
	}
	
	public static String nameDetails() {
		String payload = "{\r\n"
				+ "	\"data\":[\r\n"
				+ "      {\r\n"
				+ "         \"id\":1,\r\n"
				+ "         \"email\":\"george.bluth@reqres.in\",\r\n"
				+ "         \"first_name\":\"George\",\r\n"
				+ "         \"last_name\":\"Bluth\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "         \"id\":2,\r\n"
				+ "         \"email\":\"janet.weaver@reqres.in\",\r\n"
				+ "         \"first_name\":\"Janet\",\r\n"
				+ "         \"last_name\":\"Weaver\"\r\n"
				+ "      }\r\n"
				+ "	]\r\n"
				+ "	}";
		return payload;
	}

}
