package yape.test.request;

import org.json.JSONObject;

public class CrearReservaRequest {
	

	
	public String crearReserva(String firstname, String lastname,String totalprice, String depositpaid,String checkin, String checkout, String additionalneeds ) {
		JSONObject myObject = new JSONObject();
		myObject.put("firstname", firstname);
		myObject.put("lastname", lastname);
		myObject.put("totalprice", totalprice);
		myObject.put("depositpaid", depositpaid);
		myObject.put("additionalneeds", additionalneeds);
		JSONObject datosReserva = new JSONObject();
		datosReserva.put("checkin", checkin);
		datosReserva.put("checkout", checkout);
		myObject.put("bookingdates", datosReserva);
		
		
		return myObject.toString();
		
	}
}
