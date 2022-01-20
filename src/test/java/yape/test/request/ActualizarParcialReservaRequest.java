package yape.test.request;

import org.json.JSONObject;

public class ActualizarParcialReservaRequest {
	

	
	public String actualizarReserva(String firstname, String lastname ) {
		JSONObject myObject = new JSONObject();
		myObject.put("firstname", firstname);
		myObject.put("lastname", lastname);

		
		return myObject.toString();
		
	}
}
