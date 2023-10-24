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
	
	@SerializedName("totalprice")
	@Expose
	private int totalprice;
	
	
	@SerializedName("bookingdates")
	@Expose
	private Bookingdates bookingdates;
	
	@SerializedName("depositpaid")
	@Expose
	private boolean depositpaid;
	
	
	@SerializedName("additionalneeds")
	@Expose
	private String additionalneeds;
	
	
	
	public String getAdditionalneeds() {
		return additionalneeds;
	}
	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public Bookingdates getBookingdates() {
		return bookingdates;
	}
	public void setBookingdates(Bookingdates bookingdates) {
		this.bookingdates = bookingdates;
	}
	public boolean isDepositpaid() {
		return depositpaid;
	}
	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}
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
	
	

	
}
