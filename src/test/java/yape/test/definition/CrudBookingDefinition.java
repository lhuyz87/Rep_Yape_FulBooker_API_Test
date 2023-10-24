package yape.test.definition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

//import org.jruby.RubyProcess.Sys;


import yape.test.response.AuthResponse;
import yape.test.response.Booking;
import yape.test.response.BodyConsDetReservaResponse;
import yape.test.response.BodyCrearReservaResponse;
import yape.test.step.ActualizarReservaBookingStep;
import yape.test.step.ConsultarBookingStep;
import yape.test.step.CrearReservaBookingStep;
import yape.test.step.EliminarReservaBookingStep;
import yape.test.step.StepAutenticacion;
import yape.test.util.CucumberNewUtil;
import yape.test.util.Util;
import yape.test.util.UtilReport;
import yape.test.util.Variables;
import io.restassured.response.Response;
public class CrudBookingDefinition {

	@Steps
	ConsultarBookingStep consultarBookingStep;
	@Steps
	BodyCrearReservaResponse bodyCrearReservaResponse;
	@Steps
	UtilReport utilReport;	
	@Steps
	CrearReservaBookingStep crearReservaBookingStep;
	@Steps
	BodyConsDetReservaResponse bodyConsDetReservaResponse;
	@Steps
	ActualizarReservaBookingStep actualizarReservaBookingStep;
	@Steps
	StepAutenticacion stepAutenticacion;
	@Steps
	AuthResponse authResponse;
	@Steps
	Booking bodyActualizarReservaResponse;
	@Steps
	EliminarReservaBookingStep eliminarReservaBookingStep;
	
	

	
	
	@Given("^que accedo al servicio de \"([^\"]*)\"$")
	public void que_accedo_al_servicio_de(String servicio) {
	    // Write code here that turns the phrase above into concrete actions
		consultarBookingStep.setUriPath(servicio);
	}


	@Given("^ingreso los valores de la reserva: firstname, lastname, totalprice,depositpaid, checkin, checkout, additionalneeds$")
	public void ingreso_los_valores_de_la_reserva_firstname_lastname_totalprice_depositpaid_checkin_checkout_additionalneeds(DataTable valorReserva) {
	
		List<Map<String, String>> tableValorReserva = valorReserva.asMaps(String.class, String.class);


        	crearReservaBookingStep.firstname = tableValorReserva.get(0).get("firstname");
        	crearReservaBookingStep.lastname= tableValorReserva.get(0).get("lastname");
        	crearReservaBookingStep.totalprice= tableValorReserva.get(0).get("totalprice");
        	crearReservaBookingStep.depositpaid= tableValorReserva.get(0).get("depositpaid");
        	crearReservaBookingStep.checkin = tableValorReserva.get(0).get("checkin");
        	crearReservaBookingStep.checkin = tableValorReserva.get(0).get("checkin");
        	crearReservaBookingStep.additionalneeds = tableValorReserva.get(0).get("additionalneeds");
        	crearReservaBookingStep.ingresarValoresReserva();

	    
	}
	
	
	
	@Given("^realizo la consulta$")
	public void realizo_la_consulta() {
		
		consultarBookingStep.ejecutarServicio();
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	
	@Then("^valido que se realice correctamente la busqueda$")
	public void valido_que_se_realice_correctamente_la_busqueda() {
		consultarBookingStep.validarConsulta();
	    
	}
	
	@Given("^ejecuto el servicio de creacion$")
	public void realizo_la_creación() {
	    
		bodyCrearReservaResponse=crearReservaBookingStep.ejecutarServicio();
	}

	@Then("^valido que cree el codigo de reserva$")
	public void valido_que_cree_el_codigo_de_reserva_y_muestre_los_datos_ingresados() {
		  assertNotNull(bodyCrearReservaResponse.getBookingid());
		  utilReport.showStepMessage("Booking ID "+bodyCrearReservaResponse.getBookingid());
	    
	}

	@Given("^ingreso los parametros de busqueda: firstname y/o lastname y/o  checkin y/o checkout$")
	public void ingreso_los_parametros_de_busqueda_firstname_y_o_lastname_y_o_checkin_y_o_checkout(DataTable busqueda) {
		List<Map<String, String>> tablaBusqueda = busqueda.asMaps(String.class, String.class);
		
		String cadena="";
       
        
       
        	ConsultarBookingStep.firstname = tablaBusqueda.get(0).get("firstname");
        	ConsultarBookingStep.lastname = tablaBusqueda.get(0).get("lastname");
        	ConsultarBookingStep.checkin =	tablaBusqueda.get(0).get("checkin");	
        	ConsultarBookingStep.checkout = tablaBusqueda.get(0).get("checkout");

        	
           	
           	if(ConsultarBookingStep.firstname.compareTo("NA")!=0) {
        		ConsultarBookingStep.firstname="firstname,"+ConsultarBookingStep.firstname;
        		cadena=ConsultarBookingStep.firstname;
        	}
        
        	if(ConsultarBookingStep.lastname.compareTo("NA")!=0) {
        		ConsultarBookingStep.lastname="lastname,"+ConsultarBookingStep.lastname;
        		cadena = cadena+"-"+ ConsultarBookingStep.lastname;
        	}
     
        	if(ConsultarBookingStep.checkin.compareTo("NA")!=0) {
        		ConsultarBookingStep.checkin="checkin,"+ConsultarBookingStep.checkin;
        		cadena = cadena+"-"+ ConsultarBookingStep.checkin;
        	}
        	
        	if(ConsultarBookingStep.checkout.compareTo("NA")!=0) {
        		ConsultarBookingStep.checkout="checkout,"+ConsultarBookingStep.checkout;
        		cadena = cadena+"-"+ ConsultarBookingStep.checkout;
        	}
        	ConsultarBookingStep.parametros=cadena;
//        	System.out.println("+*******+ "  + ConsultarBookingStep.parametros);

	    
	}
	
	@Given("^ingreso el código de reserva$")
	public void ingreso_el_código_de_reserva(DataTable reserva) {
		
		List<Map<String, String>> tablaReerva = reserva.asMaps(String.class, String.class);

        ConsultarBookingStep.bookingID=tablaReerva.get(0).get("bookingID");
      
	}
	
	@Given("^realizo la consulta por código de reserva$")
	public void realizo_la_consulta_por_código_de_reserva() {
		bodyConsDetReservaResponse=consultarBookingStep.ejecutarServicioDetalleConsulta();
	    
	}

	@Then("^valido que se muestre los datos de la busqueda$")
	public void valido_que_se_muestre_los_datos_de_la_busqueda() {
		  assertNotNull(bodyConsDetReservaResponse.getFirstname());
		  assertNotNull(bodyConsDetReservaResponse.getLastname());
		  utilReport.showStepMessage("Reserva de: "+bodyConsDetReservaResponse.getFirstname() +" - " + bodyConsDetReservaResponse.getLastname());
	    
	}





	@Given("^ingreso el parámetro codigo de reserva$")
	public void ingreso_el_par_metro_codigo_de_reserva(DataTable reserva) {
		
		List<Map<String, String>> tablaReerva = reserva.asMaps(String.class, String.class);

        actualizarReservaBookingStep.bookingId=tablaReerva.get(0).get("codigo_reserva");

	    
	}
	
	@Given("^ingreso el parámetro codigo de reserva a eliminar$")
	public void ingreso_el_par_metro_codigo_de_reserva_eliminar(DataTable reserva) {
		List<Map<String, String>> tablaReerva = reserva.asMaps(String.class, String.class);

        actualizarReservaBookingStep.bookingId=tablaReerva.get(0).get("codigo_reserva");
	    
	}
	
	@Given("^genero token y accedo al servicio de \"([^\"]*)\"$")
	public void genero_token_y_accedo_al_servicio_de(String servicio) {
        stepAutenticacion.setUriPath("token");
		stepAutenticacion.ingresarDatosUsuario(Util.usuarioToken, Util.passwordToken);
		actualizarReservaBookingStep.token  = stepAutenticacion.ejecutarServicio().getToken();
		Variables.token=actualizarReservaBookingStep.token;
		actualizarReservaBookingStep.setUriPath(servicio);
	    
	}
	
	@Given("^realizo la actualización$")
	public void realizo_la_actualización() {
		actualizarReservaBookingStep.ejecutarServicio();
	   
	}
	

	@Given("^ingreso los posibles valores a modificar$")
	public void ingreso_los_posibles_valores_a_modificar(DataTable datos) {

		
		
		List<Map<String, String>> tablaDatos = datos.asMaps(String.class, String.class);


		actualizarReservaBookingStep.firstname = tablaDatos.get(0).get("firstname");

		actualizarReservaBookingStep.lastname = tablaDatos.get(0).get("lastname");
		actualizarReservaBookingStep.totalprice =tablaDatos.get(0).get("totalprice"); 
		actualizarReservaBookingStep.depositpaid =tablaDatos.get(0).get("depositpaid");
		actualizarReservaBookingStep.checkin =tablaDatos.get(0).get("checkin"); 
		actualizarReservaBookingStep.checkout =tablaDatos.get(0).get("checkout"); 
		actualizarReservaBookingStep.additionalneeds = tablaDatos.get(0).get("additionalneeds");      
		
		actualizarReservaBookingStep.ingresarValoresActualizarReserva();
	}

	@Given("^ingreso los datos para la genereación de token y realizo la actualización$")
	public void realizo_la_actualizacion(DataTable table) {
		

		actualizarReservaBookingStep.ejecutarServicio();
	}

	@Then("^valido que se muestren los datos actualizados$")
	public void valido_que_se_muestren_los_datos_actualizados() {
		actualizarReservaBookingStep.validarResultados();
	}
	
	@Given("^ingreso los valores para la actualización$")
	public void ingreso_los_valores_para_la_actualización(DataTable datos) {
		List<Map<String, String>> tablaDatos = datos.asMaps(String.class, String.class);
		actualizarReservaBookingStep.firstname = tablaDatos.get(0).get("firstname");
		actualizarReservaBookingStep.lastname = tablaDatos.get(0).get("lastname");
		actualizarReservaBookingStep.ingresarValoresActualizarParcialReserva();

	}


	@Given("^realizo la actualización de datos parciales$")
	public void realizo_la_actualizacion() {
	    // Write code here that turns the phrase above into concrete actions
		actualizarReservaBookingStep.ejecutarServicioActualParcial();
	}
	
	
	@Then("^valido que se genere la actualización de los datos$")
	public void valido_que_se_genere_los_datos_actualizados() {
		actualizarReservaBookingStep.validarResultadosActParcial();
	}



	@Given("^ejecuto la eliminación$")
	public void realizo_la_eliminaci_n() {
		eliminarReservaBookingStep.ejecutarServicioEliminar();
	    
	}

	@Then("^valido que se muestre como resultado de la eliminacion el valor \"([^\"]*)\"$")
	public void valido_que_se_muestre_como_resultado_de_la_eliminacion_el_valor(String mensajeEliminacion) {
	    eliminarReservaBookingStep.validarMensajeEliminacion(mensajeEliminacion);
	    
	}
}
