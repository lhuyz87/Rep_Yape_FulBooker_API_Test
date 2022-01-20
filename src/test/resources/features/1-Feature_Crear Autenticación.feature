Feature: Creacion de parametros para la autenticación

  @AuthToken
  Scenario: Validar que se genere token para la correcta autenticacion
    Given que accedo al servicio para la generacion de "token"
    And ingreso los valores de usuario y password
      | usuario | password    |
      | admin   | password123 |
    And ejecuto el servicio
    Then valido que se genere el numero de token
    
   @FailAuthToken
  Scenario: Validar que se genere error al enviar usuarios invalidos en la autenticación
    Given que accedo al servicio para la generacion de "token"
    And ingreso los valores de usuario y password
      | usuario | password    |
      | error   | error |
    And ejecuto el servicio
    Then valido que se muestre un mensaje de error
