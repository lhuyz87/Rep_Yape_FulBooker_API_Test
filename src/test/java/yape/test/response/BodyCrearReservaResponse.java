package yape.test.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BodyCrearReservaResponse {
	
	@SerializedName("bookingid")
	@Expose
	private long bookingid;
	
	@SerializedName("booking")
	@Expose
	private Booking booking;
	
	

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public long getBookingid() {
		return bookingid;
	}

	public void setBookingid(long bookingid) {
		this.bookingid = bookingid;
	}



	
}
