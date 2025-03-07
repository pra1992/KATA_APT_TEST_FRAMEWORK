package steps;

import config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseAPI {
	public static String Endpoint;
	public static RequestSpecification request;
	public static Response response;

	protected static String sessionToken;
	static {
		sessionToken = getSessionToken();
	}

	public static String getSessionToken() {
		
		String authURL = ConfigManager.getProperty("Login") + "auth/login";
		String username = ConfigManager.getProperty("username");
		String password = ConfigManager.getProperty("password");
		String requestBody = "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }";

		Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).post(authURL);
		response.then().log().all();
		sessionToken = response.getCookie("token");

		if (sessionToken != null) {
			System.out.println("Retrieved Session Token: " + sessionToken);
		} else {
			System.out.println("No session token received. Authentication failed!");

		}
		return sessionToken;
	}

}
