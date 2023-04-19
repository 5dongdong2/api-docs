Feature: post_get_put_delete

  Scenario:
    * configure url = 'http://localhost:8080'

    Given path '/api/test/post'
    And request { id:  null, description:  '등록합니다.'}
    When method post
    Then status 200

    Given path '/api/test/get/1'
    When method get
    Then status 200
    And assert response.id == 1
    And assert response.description == '등록합니다.'

    Given path '/api/test/put/1'
    And request { id:  1, description:  '수정합니다.'}
    When method put
    Then status 200

    Given path '/api/test/get/1'
    When method get
    Then status 200
    And assert response.id == 1
    And assert response.description == '수정합니다.'

    Given path '/api/test/delete/1'
    When method delete
    Then status 200

    Given path '/api/test/get/1'
    When method get
    Then status 200
    And assert response.id == 1
    And assert response.description == null