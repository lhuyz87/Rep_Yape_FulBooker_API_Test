package yape.test.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BodyListaBookingIdResponse<T> {
	
	@SerializedName("bookingid")
	@Expose
	private String bookingid;

	
	
	public String getBookingid() {
		return bookingid;
	}

	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}

	

	
}
