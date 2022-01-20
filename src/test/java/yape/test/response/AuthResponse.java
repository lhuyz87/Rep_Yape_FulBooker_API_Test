package yape.test.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthResponse {
	
	@SerializedName("token")
	@Expose
	private String token;

	@SerializedName("reason")
	@Expose
	private String reason;

	
	public String getToken() {
		return token;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setToken(String token) {
		this.token = token;
	}

	

	
}
