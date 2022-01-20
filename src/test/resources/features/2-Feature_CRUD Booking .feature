Feature: CRUD para la gestión de reservas

  @CrearReserva
  Scenario: Validar se realice la creación de una reserva
    Given que accedo al servicio de "Crear_Reserva"
    And ingreso los valores de la reserva: firstname, lastname, totalprice,depositpaid, checkin, checkout, additionalneeds
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Mark      | brown    |        160 | true        | 2014-03-13 | 2014-05-21 | cena            |
    And ejecuto el servicio de creacion
    Then valido que cree el codigo de reserva

  @BuscarReserva
  Scenario: Validar se realice la busqueda de las reservas
    Given que accedo al servicio de "Consulta_Reservas"
    And ingreso los parametros de busqueda: firstname y/o lastname y/o  checkin y/o checkout
      | firstname | lastname | checkin | checkout |
      | Luis      | Retamozo | NA      | NA       |
    And realizo la consulta
    Then valido que se realice correctamente la busqueda

  @BuscarDetalleReserva
  Scenario: Validar se realice la busqueda del detalle por reserva
    Given que accedo al servicio de "Consulta_Detalle_Reserva"
    And ingreso el código de reserva
      | bookingID |
      |        32 |
    And realizo la consulta por código de reserva
    Then valido que se muestre los datos de la busqueda

  @ActualizarDatosReserva
  Scenario: Validar que se realice actualizaciones en los datos de la reserva
    Given genero token y accedo al servicio de "Actualizar_Datos_Reserva"
    And ingreso el parámetro codigo de reserva
      | codigo_reserva |
      |              7 |
    And ingreso los posibles valores a modificar
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Luis      | Retamozo |        120 | true        | 2014-03-13 | 2014-05-21 | almuerzo        |
    And realizo la actualización
    Then valido que se muestren los datos actualizados

  @ActualizarDatosParcialesReserva
  Scenario: Validar que se realice actualizaciones de datos parciales de una reserva
    Given genero token y accedo al servicio de "Actualizar_Datos_Parciales_Reserva"
    And ingreso el parámetro codigo de reserva
      | codigo_reserva |
      |              7 |
    And ingreso los valores para la actualización
      | firstname | lastname |
      | Paolo     | Guerrero |
    And realizo la actualización de datos parciales
    Then valido que se genere la actualización de los datos

  @BorrarReserva
  Scenario: Validar que se realice la eliminación de una reserva
    Given genero token y accedo al servicio de "Borrar_Reserva"
    And ingreso el parámetro codigo de reserva a eliminar
      | codigo_reserva |
      |             20 |
    And ejecuto la eliminación
    Then valido que se muestre como resultado de la eliminacion el valor "Created"
