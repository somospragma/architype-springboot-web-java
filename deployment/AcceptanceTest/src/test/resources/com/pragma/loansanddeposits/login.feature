@ignore
Feature: Integracion ADP para generacion de token_id

  Este Feature se ignora en el reporte de resultados ya que es usado para la generacion del
  id_token, por lo tanto, si al menos una prueba es ejecutada de forma correcta
  se garantiza que la integracion con ADP es correcta.


  Background: loginData for ADP login
    * url urlLoginAdp
    * def loginData = read('/json/adp_login_data.json')

  @getIdToken
  Scenario: Hacer login y obtener el id_token para acceder a los endpoint de la app IP
    Given path adpDirectoryId + '/oauth2/v2.0/token'
    And form fields loginData.body
    When method POST
    Then status 200
