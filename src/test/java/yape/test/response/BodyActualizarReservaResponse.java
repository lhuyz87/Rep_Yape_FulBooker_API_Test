package yape.test.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BodyActualizarReservaResponse {
	
	@SerializedName("firstname")
	@Expose
	private String firstname;
	
	@SerializedName("lastname")
	@Expose
	private String lastname;
	
	@SerializedName("totalprice")
	@Expose
	private int totalprice;
	
	@SerializedName("additionalneeds")
	@Expose
	private String additionalneeds;
	
	@SerializedName("bookingdates")
	@Expose
	private ActualizarReservaBookingdatesResponse actualizarReservaBookingdatesResponse;
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

	public ActualizarReservaBookingdatesResponse getActualizarReservaBookingdatesResponse() {
		return actualizarReservaBookingdatesResponse;
	}

	public void setActualizarReservaBookingdatesResponse(
			ActualizarReservaBookingdatesResponse actualizarReservaBookingdatesResponse) {
		this.actualizarReservaBookingdatesResponse = actualizarReservaBookingdatesResponse;
	}


	
	
	
	

	
}
