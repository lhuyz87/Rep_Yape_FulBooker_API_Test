package yape.test.step;

import static org.junit.Assert.assertEquals;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import yape.test.request.ActualizarParcialReservaRequest;
import yape.test.request.ActualizarReservaRequest;
import yape.test.request.CrearReservaRequest;
import yape.test.response.AuthResponse;
import yape.test.response.Booking;
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
	private Booking booking;
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
	public Booking ejecutarServicio() {
		Response response =  responseServices.ejecutarServicioPutToken(Variables.endPoint, util.metodoServicio, body, bookingId,"",token);
		response.then().assertThat().statusCode(200);
		if(response.getStatusCode()==200) {
			booking = response.then().extract().body().as(Booking.class);
		}else {
			if(response.getStatusCode()!=200) {
				booking = response.then().extract().body().as(Booking.class);
			}
		}
		
		return booking;
	}
	
	public void validarResultados() {
		   assertEquals(firstname,booking.getFirstname());
		    assertEquals(lastname,booking.getLastname());
		    assertEquals(totalprice,""+booking.getTotalprice());
		    assertEquals(additionalneeds,booking.getAdditionalneeds());
		    assertEquals(checkin,booking.getBookingdates().getCheckin());
		    assertEquals(checkout,booking.getBookingdates().getCheckout());

	}
	
	public void validarResultadosActParcial() {
		   assertEquals(firstname,booking.getFirstname());
		    assertEquals(lastname,booking.getLastname());
		   

	}
	
	@Step("Se debe obtener una respuesta exitosa del servicio")
	public Booking ejecutarServicioActualParcial() {
		Response response =  responseServices.ejecutarServicioPatchToken(Variables.endPoint, util.metodoServicio, body, bookingId,"",token);
		response.then().assertThat().statusCode(200);
		if(response.getStatusCode()==200) {
			booking = response.then().extract().body().as(Booking.class);
		}else {
			if(response.getStatusCode()!=200) {
				booking = response.then().extract().body().as(Booking.class);
			}
		}

				
		return booking;
	}
	
	
	
	

	
	
	
}
