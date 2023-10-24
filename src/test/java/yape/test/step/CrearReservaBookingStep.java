package yape.test.step;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import yape.test.request.CrearReservaRequest;
import yape.test.response.AuthResponse;
import yape.test.response.BodyCrearReservaResponse;
import yape.test.util.ResponseServices;
import yape.test.util.Util;
import yape.test.util.Variables;

public class CrearReservaBookingStep {

	@Steps
	private Util util;
	@Steps
	private CrearReservaRequest crearReservaRequest;
	@Steps
	private ResponseServices responseServices;

	BodyCrearReservaResponse bodyCrearReservaResponse;
	
	
	String body="";
	public static String firstname="";
	public static String lastname="";
	public static String totalprice="";
	public static String depositpaid="";
	public static String checkin="";
	public static String checkout="";
	public static String additionalneeds="";
	public String setUriPath(String servicio) {
		
		util.obtenerRecursoMetodo(servicio);
		String uriPath= Variables.endPoint + util.metodoServicio;
		
	 return uriPath;
	
	}
	
	public void ingresarValoresReserva() {
		
		body = crearReservaRequest.crearReserva(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
	}
	
	@Step()
	public BodyCrearReservaResponse ejecutarServicio() {
		
		Response response =  responseServices.ejecutarServicioPost(Variables.endPoint, util.metodoServicio, body, "","");
		response.then().assertThat().statusCode(200);
//		System.out.println("Response"+ response.then().extract().body().asString());
		if(response.getStatusCode()==200) {
			bodyCrearReservaResponse = response.then().extract().body().as(BodyCrearReservaResponse.class);
		}else {
			if(response.getStatusCode()!=200) {
				bodyCrearReservaResponse = response.then().extract().body().as(BodyCrearReservaResponse.class);
			}
		}
		
		return bodyCrearReservaResponse;
	}
	
	
	
}
