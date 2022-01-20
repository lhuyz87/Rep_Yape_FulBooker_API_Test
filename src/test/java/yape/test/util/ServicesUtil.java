package yape.test.util;

import org.json.JSONObject;

public class ServicesUtil {
    public static String getBody(String body) {
        JSONObject jsonParams = new JSONObject(body);
        return jsonParams.toString();
    }
}
