package yape.test.util;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import yape.test.util.RestAssuredConfiguration;
import net.serenitybdd.rest.SerenityRest;


public class ResponseServices {

	
	private Response restAssuredResponse;

    public  Response ejecutarServicioPost(String uri, String path, String body, String complemento, String parameters) {
        String jsonBody = ServicesUtil.getBody(body);
        if(complemento.isEmpty()==false) {
        	complemento="/" + complemento;
        }
       
        this.restAssuredResponse  =  (Response) SerenityRest
        	    .given()
        	    .contentType("application/json") // Especifica el tipo de contenido
        	    .body(jsonBody) // Establece el cuerpo de la solicitud
        	    .when()
        	    .post(uri+path);
        
        System.out.println("******");
        
//       Response response = new RestAssuredConfiguration().getResponseTotalPost(requestSpecification.body(jsonBody), complemento);
        return this.restAssuredResponse;
    }
    
    
    public  Response ejecutarServicioPutToken(String uri, String path, String body, String complemento, String parameters, String token) {
    	String jsonBody = ServicesUtil.getBody(body);
        if(complemento.isEmpty()==false) {
        	complemento="/" + complemento;
        }
//        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(uri, path, 0);
//        requestSpecification = SerenityRest.given().contentType("application/json").accept("application/json").header("Content-Type", "application/json");
////        requestSpecification.header("Content-Type", "application/json");
//        requestSpecification.header("Cookie","token="+token);
//        
//       
//        
//       Response response = new RestAssuredConfiguration().getResponseTotalPut(requestSpecification.body(jsonBody), complemento);
        this.restAssuredResponse  =  (Response) SerenityRest
        	    .given()
        	    .contentType("application/json") // Especifica el tipo de contenido
        	    .body(jsonBody) // Establece el cuerpo de la solicitud
        	    .header("Cookie","token="+token)
        	    .when()
        	    .put(uri+path+complemento);
        
        
        return this.restAssuredResponse;
    }
    
    public  Response ejecutarServicioPatchToken(String uri, String path, String body, String complemento, String parameters, String token) {
    	String jsonBody = ServicesUtil.getBody(body);
        if(complemento.isEmpty()==false) {
        	complemento="/" + complemento;
        }
//        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(uri, path, 0);
//        requestSpecification = SerenityRest.given().contentType("application/json").accept("application/json").header("Content-Type", "application/json");
////        requestSpecification.header("Content-Type", "application/json");
//        requestSpecification.header("Cookie","token="+token);
//        
       
        
//       Response response = new RestAssuredConfiguration().getResponseTotalPatch(requestSpecification.body(jsonBody), complemento);
        
        this.restAssuredResponse  =  (Response) SerenityRest
        	    .given()
        	    .contentType("application/json") // Especifica el tipo de contenido
        	    .body(jsonBody) // Establece el cuerpo de la solicitud
        	    .header("Cookie","token="+token)
        	    .when()
        	    .patch(uri+path+complemento);
;
        return  this.restAssuredResponse;
    }
    
    
    
    public static Response ejecutarServicioGet(String uri, String path, String complemento, String parameters) {
        
        if(complemento.isEmpty()==false) {
        	complemento="/" + complemento;
        	System.out.println("Entra");
        }
        
        RequestSpecification requestSpec = SerenityRest.given();
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(uri, path, 0);
        RequestSpecification restAssuredRequest = SerenityRest.given().contentType("application/json").accept("application/json").header("Content-Type", "application/json");
        
        
//        if(parameters.isEmpty()==false) {
//        	String detParameter[]=parameters.split("-");
//        	for(int i=0; i<detParameter.length;i++) {
//        		requestSpecification = setParams(restAssuredRequest,detParameter[i].split(",")[0],detParameter[i].split(",")[1]);	
//        	}
//        	
//        }
        
        if(parameters.isEmpty()==false) {
        	String detParameter[]=parameters.split("-");
        	for(int i=0; i<detParameter.length;i++) {
        		requestSpecification = setParams(restAssuredRequest,detParameter[i].split(",")[0],detParameter[i].split(",")[1]);
        		requestSpec.param(detParameter[i].split(",")[0],detParameter[i].split(",")[1]);
        	}
        	
        }
        
        Response response = requestSpec.get(uri+path+complemento);
        
        
        
        
//       Response response = new RestAssuredConfiguration().getResponseTotalGet(requestSpecification, complemento);
        return response;
    }

    
    public  Response ejecutarServicioDelete(String uri, String path, String complemento, String parameters, String token) {
        
        if(complemento.isEmpty()==false) {
        	complemento="/" + complemento;
        	System.out.println("Entra");
        }
//        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(uri, path, 0);
//        requestSpecification = SerenityRest.given().contentType("application/json").accept("application/json").header("Content-Type", "application/json");
////      requestSpecification.header("Content-Type", "application/json");
//      requestSpecification.header("Cookie","token="+token);
        RestAssured.baseURI = uri+path+complemento;
        System.out.println("URI "   + uri+path+complemento);
        this.restAssuredResponse  = RestAssured
        	    .given()
        	    .when()
        	    .header("Content-Type", "application/json")
        	    .header("Cookie","token="+token)
        	    .delete(uri+path+complemento);
       
//       Response response = new RestAssuredConfiguration().getResponseTotalDelete(requestSpecification, complemento);
        return this.restAssuredResponse ;
    }	
    
    public static RequestSpecification setParams(RequestSpecification requestSpecification, String Campo, String Valor) {
    	requestSpecification.queryParam(Campo, Valor);
    	
    	return requestSpecification;
    }



}
