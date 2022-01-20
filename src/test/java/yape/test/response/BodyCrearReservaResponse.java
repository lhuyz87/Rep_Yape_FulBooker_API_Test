package yape.test.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BodyCrearReservaResponse {
	
	@SerializedName("bookingid")
	@Expose
	private String bookingid;

	
	
	public String getBookingId() {
		return bookingid;
	}

	public void setBookingId(String bookingid) {
		this.bookingid = bookingid;
	}

	

	
}
