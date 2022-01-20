package yape.test.request;

import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;

public class AuthRequest {
	
	
	public String crearBodyAuthToken(String username, String password ) {
		JSONObject myObject = new JSONObject();
		myObject.put("username", username);
		myObject.put("password", password);
		
				
		return myObject.toString();
		
	}

}
