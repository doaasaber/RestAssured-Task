Feature: Retrieve data of country and postal code from JSONPlaceholder

  Scenario Outline: Retrieve data verify the response
    Given the base URL is set
    When user send a GET request to : "<EndPoint1>" "<EndPoint2>"
    Then validate on response status code : "<StatusCode>"
    And validate on response not null or empty
    And validate on response contain "<EndPoint1>" "<EndPoint2>"

    Examples:
      | EndPoint1  |  | EndPoint2 |    | StatusCode |
      | AD         |  | AD100     |    | 200        |
