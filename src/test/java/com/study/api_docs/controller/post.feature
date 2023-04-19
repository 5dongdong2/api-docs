Feature: post

  Scenario: POST
    * configure url = 'http://localhost:8080'
    Given path '/api/test/post'
    And request { id:  null, description:  '등록합니다.'}
    When method post
    Then status 200