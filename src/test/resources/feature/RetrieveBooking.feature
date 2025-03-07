
Feature: Retrieve hotel room booking 

Background: 
	Given I am a customer trying to create a booking 

@GetDetails
Scenario: Retrieve and Confirm Booking Details Using Booking ID 
	Given I have already booked a hotel room and received a booking ID
    |bookingid|2|
	When I request to view the details of my booking using the booking ID 
	Then I should receive a successful confirmation for the booking details 
	And I should receive the correct booking information
     |firstname|Raghunath|
    |lastname|Palanisamy|