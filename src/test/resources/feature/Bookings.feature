Feature: Hotel Room Booking

Background:
 Given I am a customer trying to create a booking
 
 @BookingProcess
Scenario: Successfully Create a Booking with Complete Details
When I provide the following booking details and submit the booking request
|bookingid|2699|
|roomid|91|
|firstname|Richard|
|lastname| Johnson|
|depositpaid|true|
|email|richardjohnson@gmail.com|
|phone|78420369871254|
|checkin|2025-03-12|
|checkout|2025-03-15|
Then Booking is confirmed with a success message


@BookingProcess
Scenario: Retrieve and Confirm Booking Details Using Booking ID
  Given I have already booked a hotel room and received a booking ID
  When I request to view the details of my booking using the booking ID
  Then I should receive a successful confirmation for the booking details
  Then I should receive the correct booking information
