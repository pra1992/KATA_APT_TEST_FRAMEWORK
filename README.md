**Hotel Booking API Test Automation Framework**

**Project Overview**
This project is an API Test Automation framework built using Java, Rest-Assured, Cucumber, and Maven. The framework is designed to automate the testing of a hotel booking system API, providing comprehensive coverage of positive and negative test scenarios to ensure the robustness and reliability of the application.

**Objective**
The goal of this framework is to demonstrate API testing for the hotel booking service through automated tests. The framework validates various endpoints and scenarios to verify correct API behavior under different conditions, ranging from successful bookings to error handling for invalid inputs.

**Architecture**
The test framework is structured around the Page Object Model (POM) design pattern, making it easy to maintain and extend. The tests are written in Cucumber (BDD format) to provide clear and readable steps for business stakeholders, while Rest-Assured is used for API requests and responses validation.

**Rest-Assured handles HTTP requests and responses.**
-Cucumber is used for feature files and scenario steps, enabling BDD testing.
-TestNG is used for running the tests and reporting.
-Maven is used for dependency management and building the project.
-Test Coverage
-The test suite includes a variety of test scenarios, covering positive cases like successful bookings and retrieving booking details, as well as negative cases such as missing required fields, invalid formats, 
 and other edge cases. Below are the key test scenarios included:

**Positive Scenarios**
-Create a New Booking: Test that the booking is successfully created when all required fields are provided.
-Retrieve Booking Details: Ensure booking details can be fetched correctly using the booking ID.
-Booking with Mandatory Fields: Confirm that the booking can be created with only the mandatory fields.
**Negative Scenarios**
-Missing Mandatory Field (FirstName): Validate the error message when a required field, like FirstName, is missing.
-Missing Mandatory Field (LastName): Validate the error message when LastName is missing.
-Invalid Email Format: Ensure an error occurs when the email address is invalid.
-Phone Number Length Validation: Test scenarios where the phone number is too short or too long.
-Checkout Date Before Checkin Date: Confirm that a validation error occurs if the checkout date is before the checkin date.
-Invalid Room ID: Ensure that the system handles invalid room IDs gracefully.
-First Name/Last Name Length Validation: Test edge cases where the FirstName or LastName exceeds the length limits.
**Technologies Used**
-Java for programming the framework
-Rest-Assured for API testing and validation
-Cucumber for Behavior Driven Development (BDD) testing
-TestNG for managing and running the tests
-Maven for dependency management and building the project
-ExtentReports for generating test reports

**Getting Started**
To get started with this project, follow the steps below:

**Prerequisites**
-JDK 8 or higher installed
-Maven installed for dependency management and build
-Git for version control

**Setup Instructions**

- Clone this repository to your local machine:https://github.com/pra1992/KATA_APT_TEST_FRAMEWORK.git
- Navigate to the project directory:cd Hotel-Booking-API-Automation
- mvn clean install
- mvn test
- The test reports will be generated after the tests are executed. You can view the results in the target directory.

**Test Execution Flow**
The test suite is designed to be executed using TestNG. You can choose to run specific tests based on the feature or scenario.

**Dependencies**
The project uses the following key dependencies:

- Rest-Assured for API testing.
- Cucumber for BDD test execution.
- TestNG for test execution and reporting.
- Maven for build and dependency management
  
**  Contributing**
If you'd like to contribute to this repository, feel free to fork it, make changes, and submit a pull request. Any improvements to the framework or test cases are welcome.
