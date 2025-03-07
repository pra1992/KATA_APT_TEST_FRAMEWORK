Feature: Hotel Room Booking 

Background: 
	Given I am a customer trying to create a booking 
	
@BookingProcess 
Scenario: Successfully Create a Booking with Complete Details 
	When I provide the following booking details and submit the booking request 
		|bookingid|2700|
		|roomid|94|
		|firstname|Jordan|
		|lastname| Chritina|
		|depositpaid|true|
		|email|jordanchritina@gmail.com|
		|phone|78420369871259|
		|checkin|2025-03-13|
		|checkout|2025-03-15|
	Then Booking is confirmed with a success message 
	
	
@BookingProcess 
Scenario: Retrieve and Confirm Booking Details Using Booking ID 
	Given I have already booked a hotel room and received a booking ID 
	When I request to view the details of my booking using the booking ID 
	Then I should receive a successful confirmation for the booking details 
	And I should receive the correct booking information
