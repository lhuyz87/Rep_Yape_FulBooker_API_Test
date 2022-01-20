package yape.test.definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.jruby.RubyProcess.Sys;

import cucumber.api.DataTable;
import yape.test.response.AuthResponse;
import yape.test.response.BodyActualizarReservaResponse;
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
	private ConsultarBookingStep consultarBookingStep;
	@Steps
	private BodyCrearReservaResponse bodyCrearReservaResponse;
	@Steps
	private UtilReport utilReport;	
	@Steps
	private CrearReservaBookingStep crearReservaBookingStep;
	@Steps
	private BodyConsDetReservaResponse bodyConsDetReservaResponse;
	@Steps
	private ActualizarReservaBookingStep actualizarReservaBookingStep;
	@Steps
	private StepAutenticacion stepAutenticacion;
	@Steps
	private AuthResponse authResponse;
	@Steps
	private BodyActualizarReservaResponse bodyActualizarReservaResponse;
	@Steps
	private EliminarReservaBookingStep eliminarReservaBookingStep;
	
	

	
	
	@Given("^que accedo al servicio de \"([^\"]*)\"$")
	public void que_accedo_al_servicio_de(String servicio) {
	    // Write code here that turns the phrase above into concrete actions
		consultarBookingStep.setUriPath(servicio);
	}


	@Given("^ingreso los valores de la reserva: firstname, lastname, totalprice,depositpaid, checkin, checkout, additionalneeds$")
	public void ingreso_los_valores_de_la_reserva_firstname_lastname_totalprice_depositpaid_checkin_checkout_additionalneeds(DataTable table) {
		CucumberNewUtil.ConvertDataTableToDict(table);
        List<List<String>> data = table.raw();

        for (int i = 1; i < data.size(); i++) {
        	crearReservaBookingStep.firstname = CucumberNewUtil.GetCellValueWithRowIndex("firstname", i);
        	crearReservaBookingStep.lastname = CucumberNewUtil.GetCellValueWithRowIndex("lastname", i);
        	crearReservaBookingStep.totalprice = CucumberNewUtil.GetCellValueWithRowIndex("totalprice", i);
        	crearReservaBookingStep.depositpaid = CucumberNewUtil.GetCellValueWithRowIndex("depositpaid", i);
        	crearReservaBookingStep.checkin = CucumberNewUtil.GetCellValueWithRowIndex("checkin", i);
        	crearReservaBookingStep.checkout = CucumberNewUtil.GetCellValueWithRowIndex("checkout", i);
        	crearReservaBookingStep.additionalneeds = CucumberNewUtil.GetCellValueWithRowIndex("additionalneeds", i);
        	crearReservaBookingStep.ingresarValoresReserva();
        }
	    
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
		  assertNotNull(bodyCrearReservaResponse.getBookingId());
		  utilReport.showStepMessage("Booking ID "+bodyCrearReservaResponse.getBookingId());
	    
	}

	@Given("^ingreso los parametros de busqueda: firstname y/o lastname y/o  checkin y/o checkout$")
	public void ingreso_los_parametros_de_busqueda_firstname_y_o_lastname_y_o_checkin_y_o_checkout(DataTable table) {
		CucumberNewUtil.ConvertDataTableToDict(table);
		
		String cadena="";
        List<List<String>> data = table.raw();
        
        for (int i = 1; i < data.size(); i++) {
        	ConsultarBookingStep.firstname = CucumberNewUtil.GetCellValueWithRowIndex("firstname", i);
        	ConsultarBookingStep.lastname = CucumberNewUtil.GetCellValueWithRowIndex("lastname", i);
           	ConsultarBookingStep.checkin = CucumberNewUtil.GetCellValueWithRowIndex("checkin", i);
           	ConsultarBookingStep.checkout = CucumberNewUtil.GetCellValueWithRowIndex("checkout", i);
        	
           	
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
	    
	}
	
	@Given("^ingreso el código de reserva$")
	public void ingreso_el_código_de_reserva(DataTable table) {
		CucumberNewUtil.ConvertDataTableToDict(table);
        List<List<String>> data = table.raw();
        ConsultarBookingStep.bookingID=CucumberNewUtil.GetCellValueWithRowIndex("bookingID", 1);
        
	}
	
	@Given("^realizo la consulta por código de reserva$")
	public void realizo_la_consulta_por_código_de_reserva() {
		bodyConsDetReservaResponse=consultarBookingStep.ejecutarServicioDetalleConsulta();
	    
	}

	@Then("^valido que se muestre los datos de la busqueda$")
	public void valido_que_se_muestre_los_datos_de_la_busqueda() {
		  assertNotNull(bodyConsDetReservaResponse.getFirstName());
		  assertNotNull(bodyConsDetReservaResponse.getLastName());
		  utilReport.showStepMessage("Reserva de: "+bodyConsDetReservaResponse.getFirstName() +" - " + bodyConsDetReservaResponse.getLastName());
	    
	}





	@Given("^ingreso el parámetro codigo de reserva$")
	public void ingreso_el_par_metro_codigo_de_reserva(DataTable table) {
		CucumberNewUtil.ConvertDataTableToDict(table);
        List<List<String>> data = table.raw();
        actualizarReservaBookingStep.bookingId=CucumberNewUtil.GetCellValueWithRowIndex("codigo_reserva", 1);
	    
	}
	
	@Given("^ingreso el parámetro codigo de reserva a eliminar$")
	public void ingreso_el_par_metro_codigo_de_reserva_eliminar(DataTable table) {
		CucumberNewUtil.ConvertDataTableToDict(table);
        List<List<String>> data = table.raw();
        EliminarReservaBookingStep.bookingId=CucumberNewUtil.GetCellValueWithRowIndex("codigo_reserva", 1);
	    
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
	public void ingreso_los_posibles_valores_a_modificar(DataTable table) {
		CucumberNewUtil.ConvertDataTableToDict(table);
        List<List<String>> data = table.raw(); 
		actualizarReservaBookingStep.firstname = CucumberNewUtil.GetCellValueWithRowIndex("firstname", 1);
		actualizarReservaBookingStep.lastname = CucumberNewUtil.GetCellValueWithRowIndex("lastname", 1);
		actualizarReservaBookingStep.totalprice = CucumberNewUtil.GetCellValueWithRowIndex("totalprice", 1);
		actualizarReservaBookingStep.depositpaid = CucumberNewUtil.GetCellValueWithRowIndex("depositpaid", 1);
		actualizarReservaBookingStep.checkin = CucumberNewUtil.GetCellValueWithRowIndex("checkin", 1);
		actualizarReservaBookingStep.checkout = CucumberNewUtil.GetCellValueWithRowIndex("checkout", 1);
		actualizarReservaBookingStep.additionalneeds = CucumberNewUtil.GetCellValueWithRowIndex("additionalneeds", 1);       
		
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
	public void ingreso_los_valores_para_la_actualización(DataTable table) {
		CucumberNewUtil.ConvertDataTableToDict(table);
        List<List<String>> data = table.raw(); 
		actualizarReservaBookingStep.firstname = CucumberNewUtil.GetCellValueWithRowIndex("firstname", 1);
		actualizarReservaBookingStep.lastname = CucumberNewUtil.GetCellValueWithRowIndex("lastname", 1);
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
