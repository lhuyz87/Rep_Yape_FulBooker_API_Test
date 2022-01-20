Feature: Crear servicio para validar que la API este funcionando

  @PingApi
  Scenario: Validar que se muestre si la API se encuentra operativa
    Given que accedo al servicio de "Ping"
    And realizo la ejecución
    Then valido que se muestre como resultado el valor "Created"

 