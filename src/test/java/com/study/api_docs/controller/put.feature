Feature: put

  Scenario: put
    * configure url = 'http://localhost:8080'
    Given path '/api/test/put/1'
    And request { id:  1, description:  '수정합니다.'}
    When method put
    Then status 200