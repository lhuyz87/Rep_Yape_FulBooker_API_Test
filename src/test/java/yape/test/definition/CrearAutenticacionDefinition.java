package yape.test.definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.testng.Assert;

import cucumber.api.DataTable;
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
	public void ingreso_los_valores_de_usuario_y_password(DataTable table) {
		  CucumberNewUtil.ConvertDataTableToDict(table);
	        List<List<String>> data = table.raw();

	        for (int i = 1; i < data.size(); i++) {
	            String usuario = CucumberNewUtil.GetCellValueWithRowIndex("usuario", i);
	            String password = CucumberNewUtil.GetCellValueWithRowIndex("password", i);
	            stepAutenticacion.ingresarDatosUsuario(usuario, password);
	   
	        }
	    
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
