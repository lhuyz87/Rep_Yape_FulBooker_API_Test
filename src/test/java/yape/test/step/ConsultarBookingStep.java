package yape.test.step;



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
import org.testng.Assert;


public class ConsultarBookingStep {

	@Steps
	private Variables variables;
	@Steps
	private Util util;
	@Steps
	private AuthRequest authRequest;
	@Steps
	private ResponseServices responseServices;
	@Steps
	private BodyConsBookingResponse bodyConsBookingResponse;
	@Steps
	private BodyConsDetReservaResponse bodyConsDetReservaResponse;
	
	String body="";
	public static String firstname="";
	public static String lastname="";
	public static String checkin="";
	public static String checkout="";
	public static String bookingID="";
	
	public static String ResultBusqueda="";
	public static String parametros="";
	public String setUriPath(String servicio) {
		
		util.obtenerRecursoMetodo(servicio);
		String uriPath= Variables.endPoint + util.metodoServicio;
		
	 return uriPath;
		
	}

	
	
	@Step("Se debe obtener una respuesta exitosa del servicio")
	public String ejecutarServicio() {
		Response response =  responseServices.ejecutarServicioGet(Variables.endPoint, util.metodoServicio, "",parametros);
		response.then().assertThat().statusCode(200);
		if(response.getStatusCode()==200) {
			ResultBusqueda =response.then().extract().body().asString();
		}else {
			if(response.getStatusCode()!=200) {
				ResultBusqueda =response.then().extract().body().asString();
			}
		}
		
	
		return ResultBusqueda;
	}
	
	
	@Step("Se debe obtener una respuesta exitosa del servicio")
	public BodyConsDetReservaResponse ejecutarServicioDetalleConsulta() {
		Response response =  responseServices.ejecutarServicioGet(Variables.endPoint, util.metodoServicio, bookingID,"");
		response.then().assertThat().statusCode(200);
		if(response.getStatusCode()==200) {
			bodyConsDetReservaResponse = response.then().extract().body().as(BodyConsDetReservaResponse.class);
		}else {
			if(response.getStatusCode()!=200) {
			bodyConsDetReservaResponse = response.then().extract().body().as(BodyConsDetReservaResponse.class);
			}
		}
		
//		bodyConsBookingResponse.getBookinId().get(0).
		return bodyConsDetReservaResponse;
	}



	public void validarConsulta() {
		Assert.assertTrue(ResultBusqueda.contains("bookingid"));
		
	}
}
