package yape.test.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BodyConsDetReservaResponse {
	
	@SerializedName("firstname")
	@Expose
	private String firstname;
	@SerializedName("lastname")
	@Expose
	private String lastname;
	
	
	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String bookingid) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String bookingid) {
		this.lastname = lastname;
	}

	
}
