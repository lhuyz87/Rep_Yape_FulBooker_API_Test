package yape.test.step;

import static org.junit.Assert.assertEquals;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import yape.test.request.ActualizarParcialReservaRequest;
import yape.test.request.ActualizarReservaRequest;
import yape.test.request.CrearReservaRequest;
import yape.test.response.AuthResponse;
import yape.test.response.BodyActualizarReservaResponse;
import yape.test.response.BodyCrearReservaResponse;
import yape.test.util.ResponseServices;
import yape.test.util.Util;
import yape.test.util.Variables;

public class ActualizarReservaBookingStep {

	@Steps
	private Util util;
	@Steps
	private ResponseServices responseServices;
	@Steps
	private ActualizarReservaRequest actualizarReservaRequest;
	@Steps
	private BodyActualizarReservaResponse bodyActualizarReservaResponse;
	@Steps
	private ActualizarParcialReservaRequest actualizarParcialReservaRequest;
	

	String body="";
	public static String firstname="";
	public static String lastname="";
	public static String totalprice="";
	public static String depositpaid="";
	public static String checkin="";
	public static String checkout="";
	public static String additionalneeds="";
	public static String bookingId="";
	public static String token="";

	
	public String setUriPath(String servicio) {
		
		util.obtenerRecursoMetodo(servicio);
		String uriPath= Variables.endPoint + util.metodoServicio;
		
	 return uriPath;
	
	}
	
	public void ingresarValoresActualizarReserva() {
		
		body = actualizarReservaRequest.actualizarReserva(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
			
	}
	
	public void ingresarValoresActualizarParcialReserva() {
		
		body = actualizarParcialReservaRequest.actualizarReserva(firstname, lastname);
		System.out.println("BODY ANT "+ body);
	
	}
	
	@Step("Se debe obtener una respuesta exitosa del servicio")
	public BodyActualizarReservaResponse ejecutarServicio() {
		Response response =  responseServices.ejecutarServicioPutToken(Variables.endPoint, util.metodoServicio, body, bookingId,"",token);
		response.then().assertThat().statusCode(200);
		if(response.getStatusCode()==200) {
			bodyActualizarReservaResponse = response.then().extract().body().as(BodyActualizarReservaResponse.class);
		}else {
			if(response.getStatusCode()!=200) {
				bodyActualizarReservaResponse = response.then().extract().body().as(BodyActualizarReservaResponse.class);
			}
		}
		
		return bodyActualizarReservaResponse;
	}
	
	public void validarResultados() {
		   assertEquals(firstname,bodyActualizarReservaResponse.getFirstname());
		    assertEquals(lastname,bodyActualizarReservaResponse.getLastname());
		    assertEquals(totalprice,""+bodyActualizarReservaResponse.getTotalprice());
		    assertEquals(additionalneeds,bodyActualizarReservaResponse.getAdditionalneeds());
		    assertEquals(checkin,bodyActualizarReservaResponse.getActualizarReservaBookingdatesResponse().getCheckin());
		    assertEquals(checkout,bodyActualizarReservaResponse.getActualizarReservaBookingdatesResponse().getCheckout());

	}
	
	public void validarResultadosActParcial() {
		   assertEquals(firstname,bodyActualizarReservaResponse.getFirstname());
		    assertEquals(lastname,bodyActualizarReservaResponse.getLastname());
		   

	}
	
	@Step("Se debe obtener una respuesta exitosa del servicio")
	public BodyActualizarReservaResponse ejecutarServicioActualParcial() {
		Response response =  responseServices.ejecutarServicioPatchToken(Variables.endPoint, util.metodoServicio, body, bookingId,"",token);
		response.then().assertThat().statusCode(200);
		if(response.getStatusCode()==200) {
			bodyActualizarReservaResponse = response.then().extract().body().as(BodyActualizarReservaResponse.class);
		}else {
			if(response.getStatusCode()!=200) {
				bodyActualizarReservaResponse = response.then().extract().body().as(BodyActualizarReservaResponse.class);
			}
		}

				
		return bodyActualizarReservaResponse;
	}
	
	
	
	

	
	
	
}
