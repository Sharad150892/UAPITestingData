package jiraTestingAPI;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

public class JiraTest {
	
	
	public static void main(String[] args) {
		
		RestAssured.baseURI = "http://localhost:8090";
		
		
		
		//Login Scenario
		SessionFilter session = new SessionFilter();
		
		String response = given().log().all().relaxedHTTPSValidation().header("Content-Type","application/json").
							body("{ \"username\": \"sahusharad1508\", \"password\": \"Sharad@1508\" }").filter(session).
							when().post("/rest/auth/1/session").then().log().all().extract().response().asString();
		
		String expectedMessage = "Hi how are you";
		//Add comment
		
		String addCommentResponse = given().pathParam("id", "10200").log().all().header("Content-Type","application/json").
				body("{\r\n"
				+ "    \"body\": \""+expectedMessage+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{id}/comment").
				then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(addCommentResponse);
		String commentID = js.getString("id");
		
		
		//Add Attachment
		given().log().all().pathParam("id", "10200").header("X-Atlassian-Token","no-check").filter(session).
		header("Content-Type","multipart/form-data").multiPart("file", new File("jira.txt")).
		when().post("/rest/api/2/issue/{id}/attachments").
		then().log().all().assertThat().statusCode(200);
		
		
		// Get issue
		String issueDetail = given().log().all().filter(session).pathParam("id", "10200").queryParam("fields","comment").
								when().get("/rest/api/2/issue/{id}").
								then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("Issue Details = "+issueDetail);
		
		
		JsonPath js1 = new JsonPath(issueDetail);
		int commentCount = js1.getInt("fields.comment.comments.size()");
		
		for(int i=0; i<commentCount; i++) {
			String commentIdIssue =  js1.get("fields.comment.comments["+i+"].id").toString();
			
			if(commentIdIssue.equalsIgnoreCase(commentID)) {
				String message = js1.get("fields.comment.comments["+i+"].body").toString();
				System.out.println(message);
				Assert.assertEquals(message,expectedMessage);
			}
		}
		
		
		
		
		
	}

}
