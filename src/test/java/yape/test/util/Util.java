package yape.test.util;



import net.thucydides.core.annotations.Steps;

public class Util {
	
	@Steps
	private Variables variables;
	public static String metodoServicio;
	public static String method;
	public static String usuarioToken="admin";
	public static String passwordToken="password123";
	

	
	public void obtenerRecursoMetodo(String opcion) {
		switch(opcion) {
		
			case "token":				
				metodoServicio="auth";
				method="POST";
				break;
				
			case "Crear_Reserva":
				metodoServicio="booking";
				method="POST"; 
				break;
			case "Consulta_Reservas":
				metodoServicio="booking";
				method="GET";
				break;
			case "Consulta_Detalle_Reserva":
				metodoServicio="booking";
				method="GET";
				break;
			case "Actualizar_Datos_Reserva":
				metodoServicio="booking";
				method="PUT";
				break;
			case "Actualizar_Datos_Parciales_Reserva":
				metodoServicio="booking";
				method="PATCH";
				break;
			case "Borrar_Reserva":
				metodoServicio="booking";
				method="DELETE";
				break;
			case "Ping":
				metodoServicio="ping";
				method="GET";
				break;
				
				
			default:
				break;		
		}		
	}
	

}
