package yape.test.definition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.datatable.*;
import yape.test.response.AuthResponse;
import yape.test.step.StepAutenticacion;
import yape.test.util.CucumberNewUtil;
import yape.test.util.Util;
import yape.test.util.UtilReport;
import io.restassured.response.Response;
public class CrearAutenticacionDefinition {

	@Steps
	private StepAutenticacion stepAutenticacion;
	@Steps
	private AuthResponse authResponse;
	@Steps
	private UtilReport utilReport;	
	
	
	@Given("^que accedo al servicio para la generacion de \"([^\"]*)\"$")
	public void que_accedo_al_servicio_para_la_generaci_n_de(String servicio) {
	    
		stepAutenticacion.setUriPath(servicio);
	}


	@And("^ingreso los valores de usuario y password$")
	public void ingreso_los_valores_de_usuario_y_password(DataTable credenciales) {
		  
	        List<Map<String, String>> tableCrendeciales = credenciales.asMaps(String.class, String.class);

	
	            String usuario = tableCrendeciales.get(0).get("usuario");
	            String password = tableCrendeciales.get(0).get("password");
	            stepAutenticacion.ingresarDatosUsuario(usuario, password);
	
	    
	}
	
	@Then("^ejecuto el servicio$")
	public void ejecuto_servicio_de_token() {
		authResponse = stepAutenticacion.ejecutarServicio();
	    
	}

	@Then("^valido que se genere el numero de token$")
	public void valido_que_se_genere_el_n_mero_de_token() {
		assertNotNull(authResponse.getToken());
		utilReport.showStepMessage("Número de Token "+authResponse.getToken());
	}
	
	@Then("^valido que se muestre un mensaje de error$")
	public void valido_que_se_muestre_un_mensaje_de_error() {
	   Assert.assertTrue(authResponse.getReason().contains("Bad credentials"));
	
}
}
