Feature: get

  Scenario: GET
    * configure url = 'http://localhost:8080'
    Given path '/api/test/get/1'
    When method get
    Then status 200
    And assert response.id == 1
    And assert response.description == null