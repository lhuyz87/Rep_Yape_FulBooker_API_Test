package yape.test.response;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  BodyConsBookingResponse<T> {
	
	@SerializedName("")
	@Expose
	private List<BodyListaBookingIdResponse> bodyListaBookingIdResponse;

	
	
	public List<BodyListaBookingIdResponse> getBookinId() {
		return bodyListaBookingIdResponse;
	}

	public void setBookinId(List<BodyListaBookingIdResponse> bodyListaBookingIdResponse) {
		this.bodyListaBookingIdResponse = bodyListaBookingIdResponse;
	}

	

	
}
