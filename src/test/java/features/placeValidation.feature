Feature: Validating Place API's

  Scenario Outline: verify if place is successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<address>" "<language>"
    When user calls "AddPlaceAPI" with post http request
    Then the response should have status code of 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    Examples: 
      | name       | address             | language |
      | Kutty kaka | 19 Deramore Gardens | Hindi    |
      | Sonti Mama | Bangalore           | Kanad    |
