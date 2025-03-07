Feature: Hotel Room Booking

  Background:
    Given I am a customer trying to create a booking

  @BookingProcess
  Scenario: Successfully Create a Booking with Complete Details
    When I provide the following booking details and submit the booking request
      | bookingid   |                     36022 |
      | roomid      |                       142 |
      | firstname   | Raghunath                 |
      | lastname    | Palanisamy                |
      | depositpaid | true                      |
      | email       | raghunathpalani@gmail.com |
      | phone       |           784203698712852 |
      | checkin     |                2025-03-15 |
      | checkout    |                2025-03-22 |
    Then Booking is confirmed with a success message

  @BookingProcessMandatoryInputs
  Scenario: Booking with Mandatory Fields Only
    When I provide the following booking details and submit the booking request
      | firstname   | Alexandar                 |
      | lastname    | Wilson                    |
      | depositpaid | true                      |
      | email       | alexandarwilson@gmail.com |
      | phone       |             2013547865777 |
      | checkin     |                2025-03-12 |
      | checkout    |                2025-03-15 |
    Then Booking is confirmed with a success message

  @NegativeTest1
  Scenario: Missing mandatory field 'FirstName'
    When I provide the following booking details and submit the booking request
      | bookingid   |                   296 |
      | roomid      |                    67 |
      | lastname    | Pant                  |
      | depositpaid | true                  |
      | email       | rishabhpant@gmail.com |
      | phone       |           20135478662 |
      | checkin     |            2025-03-10 |
      | checkout    |            2025-03-13 |
    Then I should receive a 400 error code and the message "Firstname should not be blank"

  @NegativeTest2
  Scenario: Missing mandatory field 'LastName'
    When I provide the following booking details and submit the booking request
      | bookingid   |                   296 |
      | roomid      |                    67 |
      | firstname   | Rishabh               |
      | depositpaid | true                  |
      | email       | rishabhpant@gmail.com |
      | phone       |           20135478662 |
      | checkin     |            2025-03-10 |
      | checkout    |            2025-03-13 |
    Then I should receive a 400 error code and the message "Lastname should not be blank"

  @NegativeTest3
  Scenario: Booking with Invalid Email Format
    When I provide the following booking details and submit the booking request
      | bookingid   |         296 |
      | roomid      |          67 |
      | firstname   | Rishabh     |
      | lastname    | Pant        |
      | depositpaid | true        |
      | email       | rishabhpant |
      | phone       | 20135478662 |
      | checkin     |  2025-03-10 |
      | checkout    |  2025-03-13 |
    Then I should receive a 400 error code and the message "must be a well-formed email address" for invalid "email"

  @NegativeTest4
  Scenario: Booking with Phone Number Shorter than Required Length
    When I provide the following booking details and submit the booking request
      | bookingid   |                   296 |
      | roomid      |                    67 |
      | firstname   | Rishabh               |
      | lastname    | Pant                  |
      | depositpaid | true                  |
      | email       | rishabhpant@gmail.com |
      | phone       |                 20135 |
      | checkin     |            2025-03-10 |
      | checkout    |            2025-03-13 |
    Then I should receive a 400 error code and the message "size must be between 11 and 21" for invalid "phone"

  @NegativeTest5
  Scenario: Booking with Phone Number Larger than Required Length
    When I provide the following booking details and submit the booking request
      | bookingid   |                    296 |
      | roomid      |                     67 |
      | firstname   | Rishabh                |
      | lastname    | Pant                   |
      | depositpaid | true                   |
      | email       | rishabhpant@gmail.com  |
      | phone       | 7410236987563012458763 |
      | checkin     |             2025-03-10 |
      | checkout    |             2025-03-13 |
    Then I should receive a 400 error code and the message "size must be between 11 and 21" for invalid "phone"

  @NegativeTest6
  Scenario: Booking with Checkout date past to the Checkin Date
    When I provide the following booking details and submit the booking request
      | bookingid   |                   296 |
      | roomid      |                    67 |
      | firstname   | Rishabh               |
      | lastname    | Pant                  |
      | depositpaid | true                  |
      | email       | rishabhpant@gmail.com |
      | phone       |           20135478662 |
      | checkin     |            2025-03-16 |
      | checkout    |            2025-03-06 |
    Then I should receive a 400 error code and the message "invalid checkout date" for invalid "checkout"

  @NegativeTest7
  Scenario: Booking with Invalid Room ID
    When I provide the following booking details and submit the booking request
      | bookingid   |                    -1 |
      | roomid      |                    74 |
      | firstname   | Grand                 |
      | lastname    | Flower                |
      | depositpaid | true                  |
      | email       | andyflowert@gmail.com |
      | phone       |           20135478669 |
      | checkin     |            2025-03-11 |
      | checkout    |            2025-03-14 |
    Then I should receive a 400 error code and the message "must be greater than or equal to 1" for invalid "phone"

  @NegativeTest8
  Scenario: Invalid Firstname Length (Less Than 3 Characters)
    When I provide the following booking details and submit the booking request
      | bookingid   |                   296 |
      | roomid      |                    67 |
      | firstname   | Ri                    |
      | lastname    | Pant                  |
      | depositpaid | true                  |
      | email       | rishabhpant@gmail.com |
      | phone       |           20135478662 |
      | checkin     |            2025-03-10 |
      | checkout    |            2025-03-13 |
    Then I should receive a 400 error code and the message "size must be between 3 and 18" for invalid "firstname"

  @Negative9
  Scenario: Invalid Firstname Length (More Than 18 Characters)
    When I provide the following booking details and submit the booking request
      | bookingid   |                          296 |
      | roomid      |                           67 |
      | firstname   | Rijklopgdsbnmklopertyuihjklo |
      | lastname    | Pant                         |
      | depositpaid | true                         |
      | email       | rishabhpant@gmail.com        |
      | phone       |                  20135478662 |
      | checkin     |                   2025-03-10 |
      | checkout    |                   2025-03-13 |
    Then I should receive a 400 error code and the message "size must be between 3 and 18" for invalid "firstname"
