Feature: Find Doctors Page


  Scenario: select city and speciality
    Given User navigate to find Doctors page
    When User search for city
  	And User search for speciality
  	Then User click on speciality
  	

  Scenario: Filter doctors and extract doctors details
  	
  	Given User select Patient Story from dropdown
  	And User select Experience from dropdown
		And User select Fees and Availability
		Then User select Relevance from the dropdown 
		And User display the doctors details
