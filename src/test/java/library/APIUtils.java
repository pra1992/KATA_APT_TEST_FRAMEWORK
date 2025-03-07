package library;

import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import steps.BaseAPI;

public class APIUtils extends BaseAPI {

	/**
	 * To send a POST Request with basic Authentication
	 * 
	 * @param endpoint
	 * @param username
	 * @param password
	 * @param body
	 * @return Response
	 */
	public static Response sendPostWithBasicAuth(String endpoint, String username, String password, String body) {
		return RestAssured.given().log().all().contentType(ContentType.JSON).body(body).post(endpoint);

	}

	/**
	 * To convert the input as dataTable from Feature file and convert to JSON and
	 * eventually to String
	 * 
	 * @param dataTable
	 * @return String
	 */
	public static String convertToJSON(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap(String.class, String.class);
		StringBuilder jsonBody = new StringBuilder("{");
		String checkin = data.get("checkin"); // Fetch checkin value directly from the map
		String checkout = data.get("checkout");
		for (Map.Entry<String, String> entry : data.entrySet()) {
			String Key = entry.getKey();
			String Value = entry.getValue();
			jsonBody.append("\"").append(Key).append("\": ");
			if (Value.matches("^-?\\d+$")) {
				jsonBody.append(Value);
			} else if (Value.equalsIgnoreCase("true") || Value.equalsIgnoreCase("false")) {
				jsonBody.append(Value);
			} else {
				jsonBody.append("\"").append(Value).append("\"");
			}
			jsonBody.append(",");

		}

		jsonBody.append("\"bookingdates\": {");
		jsonBody.append("\"checkin\": \"").append(checkin).append("\", ");
		jsonBody.append("\"checkout\": \"").append(checkout).append("\"");
		jsonBody.append("}, ");

		if (jsonBody.length() > 1) {
			jsonBody.setLength(jsonBody.length() - 2);
		}
		jsonBody.append("}");
		return jsonBody.toString();
	}

	/**
	 * Retrieves the Status Message from the header of the Response
	 * 
	 * @return String
	 */
	public static String getStatusMessage() {
		String[] strings = response.getStatusLine().split(" ", 3);
		return strings[strings.length - 1];
	}

	/**
	 * To Retrieve the value for a booking id
	 * 
	 * @param endpoint accepts URL with valid booking id
	 * @return Response
	 */
	public static Response sendGetWithSession(String endpoint) {
		return RestAssured.given().header("Cookie", "token=" + sessionToken).log().uri().get(endpoint);
	}

	/**
	 * To get the response body of an endpoint as a String
	 * 
	 * @return String
	 */
	public static String getResponseBody() {
		return response.getBody().asString();
	}

}
