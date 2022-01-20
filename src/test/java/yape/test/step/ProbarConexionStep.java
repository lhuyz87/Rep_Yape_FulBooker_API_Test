package yape.test.step;

import org.testng.Assert;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import yape.test.response.AuthResponse;
import yape.test.util.ResponseServices;
import yape.test.util.Util;
import yape.test.util.Variables;

public class ProbarConexionStep {

	@Steps
	private ResponseServices responseServices;	
	@Steps
	private Util util;
	
	public static String respuesta="";
	
	@Step("Se debe obtener una respuesta exitosa del servicio - 201")
	public String ejecutarServicio() {
		Response response =  responseServices.ejecutarServicioGet(Variables.endPoint, util.metodoServicio, "","");
		response.then().assertThat().statusCode(201);
		if(response.getStatusCode()==201) {
			respuesta = response.then().extract().body().asString();
		}else {
			if(response.getStatusCode()!=200) {
			respuesta = response.then().extract().body().asString();
			}
		}
		
		return respuesta;
	}
	
	public void validarRespuesta() {
		Assert.assertTrue(respuesta.contains("Created"));
	}
}
