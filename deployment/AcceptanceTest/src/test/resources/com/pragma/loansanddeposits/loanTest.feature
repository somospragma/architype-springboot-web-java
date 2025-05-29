@acceptanceTest
Feature: Pruebas asociadas Loans

  Background:
    * url urlBase
    * def path = 'get-attachments-filter'
    * def body = read('classpath:json/loanBody.json')
    * configure headers = { ...headers, transactionId: "" }
    * def body = read('/json/postFilterComment.json')

  Scenario: Creación de loan correcta
    Given path 'loans'
    And request body.case1
    When method POST
    Then status 201
    And match response.amount == body.case1.amount
    And match response.interestRate == body.case1.interestRate
    And match response.startDate == body.case1.startDate
    And match response.endDate == body.case1.endDate

  Scenario: Creación de loan con campos faltantes
    Given path 'loans'
    And request body.empty
    When method POST
    Then status 400
    And match response.error contains 'faltantes' || response.error contains 'missing'

  Scenario: Buscar loan por id
    Given path 'loans', '1'
    When method GET
    Then status 200
    And match response.id == '1'

