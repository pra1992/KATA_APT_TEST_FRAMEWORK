package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import library.APIUtils;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class BookingSteps extends BaseAPI {

	public static int bookingId;
	public static String storedLastName;

	@Given("I am a customer trying to create a booking")
	public void setUp() {
		request = given().log().all();
	}

	@When("I provide the following booking details and submit the booking request")
	public void sendBookingDetails(DataTable BookingDetails) {
		response = APIUtils.sendPostWithBasicAuth("", "", "", APIUtils.convertToJSON(BookingDetails));
	}

	@Then("Booking is confirmed with a success message")
	public void verifyBookingConfirmation() {
		response.then().log().all();
		MatcherAssert.assertThat("Booking Failure-->Response Code is NOT 200, Please check!", response.getStatusCode(),
				Matchers.equalTo(201));
		MatcherAssert.assertThat("Booking Failure--> Status Message NOT EQUAL OK,Please check!",
				APIUtils.getStatusMessage(), Matchers.equalToIgnoringCase("OK"));
		MatcherAssert.assertThat("Booking Failure--> No Success Message, Please check!", Matchers.notNullValue(),
				Matchers.not(Matchers.equalTo("")));
	}

	@Given("I have already booked a hotel room and received a booking ID")
	public void getBookingId(DataTable fetchBookingId) {
		Map<String, Integer> Bookingdetails = fetchBookingId.asMap(String.class, Integer.class);
		bookingId = Bookingdetails.get("bookingid");
		// bookingId = Integer.parseInt(response.jsonPath().getString("bookingid"));
		MatcherAssert.assertThat("Invalid Bookingid-->Booking ID is not a valid int32, Please check!", bookingId,
				Matchers.allOf(Matchers.greaterThanOrEqualTo(Integer.MIN_VALUE),
						Matchers.lessThanOrEqualTo(Integer.MAX_VALUE)));
	}

	@When("I request to view the details of my booking using the booking ID")
	public void getDetailsOfBooking() {
		String GetEndpoint = RestAssured.baseURI + bookingId;
		response = APIUtils.sendGetWithSession(GetEndpoint);
		response.then().log().all();

	}

	@Then("I should receive a successful confirmation for the booking details")
	public void bookingDetailsSuccessfullyRetrieved() {
		MatcherAssert.assertThat("Unable to get the booking details-->Response Code is NOT 200, Please check!",
				response.getStatusCode(), Matchers.equalTo(200));
	}

	@Then("I should receive the correct booking information")
	public void bookingDetailsCorrectlyRetrieved(DataTable bookingDetails) {
		Map<String, String> PersonDetails = bookingDetails.asMap(String.class, String.class);
		String responseFirstName = PersonDetails.get("firstname");
		String responseLastName = PersonDetails.get("lastname");
		String RetrievedFirstName = response.jsonPath().getString("firstname");
		String RetrievedLastName = response.jsonPath().getString("lastname");
		MatcherAssert.assertThat("X First name mismatch-->Please check!", responseFirstName.trim(),
				Matchers.equalTo(RetrievedFirstName.trim()));
		MatcherAssert.assertThat("X Last name mismatch-->Please check!", responseLastName.trim(),
				Matchers.equalTo(RetrievedLastName.trim()));
	}

	@Then("I should receive a {int} error code and the message {string}")
	public void validateMissingField(int ErrorCode, String ExpectedMessage) {
		response.then().log().all();
		MatcherAssert.assertThat(
				"Can submit booking request without required information-->Response Code is NOT 400, Please check!",
				response.getStatusCode(), Matchers.equalTo(ErrorCode));
		String fieldError = response.jsonPath().getString("fieldErrors[0]");
		String FailureMessage = "Failure error message mismatch! Expected: " + ExpectedMessage + ", but got: "
				+ fieldError;
		MatcherAssert.assertThat(FailureMessage, fieldError.trim(), Matchers.containsString(ExpectedMessage.trim()));
	}

	@Then("I should receive a {int} error code and the message {string} for invalid {string}")
	public void validateErrorMessage(int ErrorCode, String ExpectedMessage, String InvalidFieldName) {
		response.then().log().all();
		MatcherAssert.assertThat(
				"Can Submit Booking Request with invalid" + " " + InvalidFieldName
						+ "-->Response Code is NOT 400, Please check!",
				response.getStatusCode(), Matchers.equalTo(ErrorCode));
		String errorMessage = response.jsonPath().getString("errorMessage");
		// Validate that the error message contains the expected field label
		String fieldLabelFailureMessage = "Field label mismatch! Expected: " + InvalidFieldName
				+ ", but errorMessage does not contain it.";
		MatcherAssert.assertThat(fieldLabelFailureMessage, errorMessage, Matchers.containsString(InvalidFieldName));

		String fieldError = response.jsonPath().getString("fieldErrors[0]");
		String FailureMessage = "Failure error message mismatch! Expected: " + ExpectedMessage + ", but got: "
				+ fieldError;
		MatcherAssert.assertThat(FailureMessage, fieldError.trim(), Matchers.containsString(ExpectedMessage.trim()));
	}

	@Then("I should receive an error message stating {string}")
	public void I_should_receive_an_error_message_stating(String ExpectedMessage) {
		String fieldError = response.jsonPath().getString("error");
		String FailureMessage = "Failure error message mismatch! Expected: " + ExpectedMessage + ", but got: "
				+ fieldError;
		MatcherAssert.assertThat(FailureMessage, fieldError.trim(), Matchers.containsString(ExpectedMessage.trim()));

	}

	@Then("I should not be able to retrieve any record")
	public void I_should_not_be_able_to_retrieve_any_record() {
		MatcherAssert.assertThat(
				"Error is not thrown upon giving invalid booking id -->Response Code is NOT 400 series, Please check!",
				response.getStatusCode(), Matchers.greaterThanOrEqualTo(400));
	}

}
