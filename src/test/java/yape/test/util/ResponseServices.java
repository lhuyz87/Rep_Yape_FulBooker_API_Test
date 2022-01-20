package yape.test.util;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import yape.test.util.RestAssuredConfiguration;

public class ResponseServices {

	
	

    public static Response ejecutarServicioPost(String uri, String path, String body, String complemento, String parameters) {
        String jsonBody = ServicesUtil.getBody(body);
        if(complemento.isEmpty()==false) {
        	complemento="/" + complemento;
        }
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(uri, path, 0);
        requestSpecification.header("Content-Type", "application/json");
        RequestSpecification restAssuredRequest = SerenityRest.given().contentType("application/json").accept("application/json").header("Content-Type", "application/json");
        
       
        
       Response response = new RestAssuredConfiguration().getResponseTotalPost(requestSpecification.body(jsonBody), complemento);
        return response;
    }
    
    
    public static Response ejecutarServicioPutToken(String uri, String path, String body, String complemento, String parameters, String token) {
    	String jsonBody = ServicesUtil.getBody(body);
        if(complemento.isEmpty()==false) {
        	complemento="/" + complemento;
        }
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(uri, path, 0);
        requestSpecification = SerenityRest.given().contentType("application/json").accept("application/json").header("Content-Type", "application/json");
//        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.header("Cookie","token="+token);
        
       
        
       Response response = new RestAssuredConfiguration().getResponseTotalPut(requestSpecification.body(jsonBody), complemento);
        return response;
    }
    
    public static Response ejecutarServicioPatchToken(String uri, String path, String body, String complemento, String parameters, String token) {
    	String jsonBody = ServicesUtil.getBody(body);
        if(complemento.isEmpty()==false) {
        	complemento="/" + complemento;
        }
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(uri, path, 0);
        requestSpecification = SerenityRest.given().contentType("application/json").accept("application/json").header("Content-Type", "application/json");
//        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.header("Cookie","token="+token);
        
       
        
       Response response = new RestAssuredConfiguration().getResponseTotalPatch(requestSpecification.body(jsonBody), complemento);
;
        return response;
    }
    
    
    
    public static Response ejecutarServicioGet(String uri, String path, String complemento, String parameters) {
        
        if(complemento.isEmpty()==false) {
        	complemento="/" + complemento;
        	System.out.println("Entra");
        }
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(uri, path, 0);
        RequestSpecification restAssuredRequest = SerenityRest.given().contentType("application/json").accept("application/json").header("Content-Type", "application/json");
        
        
        if(parameters.isEmpty()==false) {
        	String detParameter[]=parameters.split("-");
        	for(int i=0; i<detParameter.length;i++) {
        		requestSpecification = setParams(restAssuredRequest,detParameter[i].split(",")[0],detParameter[i].split(",")[1]);	
        	}
        	
        }
        
       Response response = new RestAssuredConfiguration().getResponseTotalGet(requestSpecification, complemento);
        return response;
    }

    
    public static Response ejecutarServicioDelete(String uri, String path, String complemento, String parameters, String token) {
        
        if(complemento.isEmpty()==false) {
        	complemento="/" + complemento;
        	System.out.println("Entra");
        }
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(uri, path, 0);
        requestSpecification = SerenityRest.given().contentType("application/json").accept("application/json").header("Content-Type", "application/json");
//      requestSpecification.header("Content-Type", "application/json");
      requestSpecification.header("Cookie","token="+token);

       
       Response response = new RestAssuredConfiguration().getResponseTotalDelete(requestSpecification, complemento);
        return response;
    }	
    
    public static RequestSpecification setParams(RequestSpecification requestSpecification, String Campo, String Valor) {
    	requestSpecification.queryParam(Campo, Valor);
    	
    	return requestSpecification;
    }



}
