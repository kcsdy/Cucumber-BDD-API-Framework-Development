Feature: Validating Place API's

Scenario Outline: verify if place is successfully added using AddPlaceAPI
	Given Add Place Payload with <name> <language> <address>
	When user calls "AddPlaceAPI" with post http request
	Then the response should have status code of 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	
Examples:
		|name 				|language |address     |
		|Dream house	|English	|Marine Drive|