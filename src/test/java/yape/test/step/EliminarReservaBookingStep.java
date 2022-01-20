package yape.test.step;

import static org.junit.Assert.assertEquals;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import yape.test.util.Util;
import yape.test.util.Variables;
import yape.test.request.AuthRequest;
import yape.test.response.AuthResponse;
import yape.test.response.BodyConsBookingResponse;
import yape.test.response.BodyConsDetReservaResponse;
import yape.test.util.ResponseServices;

public class EliminarReservaBookingStep {

	@Steps
	private Variables variables;
	@Steps
	private Util util;
	@Steps
	private ResponseServices responseServices;

	public static String respResponse="";
	public static String bookingId="";
	
	public String setUriPath(String servicio) {
		
		util.obtenerRecursoMetodo(servicio);
		String uriPath= Variables.endPoint + util.metodoServicio;
		
	 return uriPath;
		
	}
	//incluir parametros para el servico de busqueda booking
	
	
	@Step("Se debe obtener una respuesta exitosa del servicio")
	public String ejecutarServicioEliminar() {
		Response response =  responseServices.ejecutarServicioDelete(Variables.endPoint, util.metodoServicio, bookingId,"",Variables.token);
		
		response.then().assertThat().statusCode(201);
		if(response.getStatusCode()==201) {
			respResponse= response.then().extract().body().asString();
		}else {
			if(response.getStatusCode()!=200) {
				respResponse= response.then().extract().body().asString();
			}
		}
		
		return respResponse;
	}
	
	
	public void validarMensajeEliminacion(String mensajeEsperado) {
		
		assertEquals(respResponse, mensajeEsperado);
	}
	
	
}
