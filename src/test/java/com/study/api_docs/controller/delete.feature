Feature: delete

  Scenario: DELETE
    * configure url = 'http://localhost:8080'
    Given path '/api/test/delete/1'
    When method delete
    Then status 200