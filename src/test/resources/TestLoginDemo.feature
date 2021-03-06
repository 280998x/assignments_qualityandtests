Feature: Test User Login

	Scenario: User logins successfully with credentials
		Given browser is open
		And user is in login page
		When user enters correct username and password
		And user clicks login button
		Then website shows main page
		
	Scenario: User logins wrongly with credentials
		Given browser is open
		And user is in login page
		When user enters wrong username and password
		And user clicks login button
		Then login failed