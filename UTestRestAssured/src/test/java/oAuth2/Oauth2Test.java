package oAuth2;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;


public class Oauth2Test {

	public static void main(String[] args) throws InterruptedException {
		
		
		/*WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().getPageLoadTimeout();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		
		WebElement email = driver.findElement(By.id("identifierId"));
		email.sendKeys("pranotiallewar98@gmail.com");
		email.sendKeys(Keys.ENTER);
//		WebElement next =driver.findElement(By.xpath("//span[text()='Next']"));
//		next.click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		WebElement pwd = driver.findElement(By.xpath("//input[@type='password']"));
		pwd.sendKeys("pranoti@1403");
		pwd.sendKeys(Keys.ENTER);
//		next.click();
		Thread.sleep(2000);
		String url = driver.getCurrentUrl();*/
		
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AfgeXvvzrzlA6kSFt3wlSg7PbZb6M_8uQwM-OWPxKQPWdxoxscXTTA27rWDYlP3NCe8OuA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=2&prompt=none";
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&scope")[0];
		System.out.println(code);
		
		String accessTokenResponse = given().log().all().urlEncodingEnabled(false).queryParams("code",code).
		queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
		queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php").
		queryParams("grant_type","authorization_code").
	    when().post("https://www.googleapis.com/oauth2/v4/token").then().log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		
		GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON).
		when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getExpertise());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
	
		List<Api> apiCourse = gc.getCourses().getApi();
		for(int i=0; i<apiCourse.size(); i++) {
			
		}
	}

}
