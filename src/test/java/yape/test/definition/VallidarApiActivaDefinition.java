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

//import org.jruby.RubyProcess.Sys;

import yape.test.response.AuthResponse;
import yape.test.response.Booking;
import yape.test.response.BodyConsDetReservaResponse;
import yape.test.response.BodyCrearReservaResponse;
import yape.test.step.ActualizarReservaBookingStep;
import yape.test.step.ConsultarBookingStep;
import yape.test.step.CrearReservaBookingStep;
import yape.test.step.EliminarReservaBookingStep;
import yape.test.step.ProbarConexionStep;
import yape.test.step.StepAutenticacion;
import yape.test.util.CucumberNewUtil;
import yape.test.util.Util;
import yape.test.util.UtilReport;
import yape.test.util.Variables;
import io.restassured.response.Response;
public class VallidarApiActivaDefinition {

	@Steps
	private ConsultarBookingStep consultarBookingStep;
	@Steps
	private BodyCrearReservaResponse bodyCrearReservaResponse;
	@Steps
	private UtilReport utilReport;	
	@Steps
	private ProbarConexionStep probarConexionStep;	
	
	

	@Given("^realizo la ejecución$")
	public void realizo_la_ejecuci_n() {
	    // Write code here that turns the phrase above into concrete actions
		probarConexionStep.ejecutarServicio();
	}

	@Then("^valido que se muestre como resultado el valor \"([^\"]*)\"$")
	public void valido_que_se_muestre_como_resultado_el_valor(String arg1) {
	    // Write code here that turns the phrase above into concrete actions
		probarConexionStep.validarRespuesta();
		
	}

	
	
}
