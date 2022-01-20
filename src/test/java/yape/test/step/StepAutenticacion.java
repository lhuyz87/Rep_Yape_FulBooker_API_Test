package yape.test.step;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import yape.test.util.Util;
import yape.test.util.Variables;
import yape.test.request.AuthRequest;
import yape.test.response.AuthResponse;
import yape.test.util.ResponseServices;

public class StepAutenticacion {

	@Steps
	private Variables variables;
	@Steps
	private Util util;
	@Steps
	private AuthRequest authRequest;
	@Steps
	private ResponseServices responseServices;
	@Steps
	private AuthResponse authResponse;
	
	String body="";
	
	public String setUriPath(String servicio) {
		
		util.obtenerRecursoMetodo(servicio);
		String uriPath= Variables.endPoint + util.metodoServicio;
		
	 return uriPath;
		
	}
	
	
	public void ingresarDatosUsuario(String usuario, String password) {
		
		body = authRequest.crearBodyAuthToken(usuario, password);
		
		
	}
	
	@Step("Se debe obtener una respuesta exitosa del servicio")
	public AuthResponse ejecutarServicio() {
		Response response =  responseServices.ejecutarServicioPost(Variables.endPoint, util.metodoServicio, body, "","");
		response.then().assertThat().statusCode(200);
		if(response.getStatusCode()==200) {
			authResponse = response.then().extract().body().as(AuthResponse.class);
		}else {
			if(response.getStatusCode()!=200) {
				authResponse = response.then().extract().body().as(AuthResponse.class);
			}
		}
		
		return authResponse;
	}
	
}
