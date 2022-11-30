package files;

public class PayLoad {
	public static String addBookPayLoad(String isbn, String aisle) {

		String payload = "{\r\n" + "\r\n" + "\"name\":\"Learn Selenium Automation with Java BDD cucumber\",\r\n"
				+ "\"isbn\":\"" + isbn + "\",\r\n" + "\"aisle\":\"" + aisle + "\",\r\n"
				+ "\"author\":\"Sharad Sahu\"\r\n" + "}";

		return payload;
	}

}
