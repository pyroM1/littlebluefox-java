package io.littlebluefox;

// import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;

public class Client {
    public static final String defaultEndpointURL = "https://events.littlebluefox.io/";
    public static final Integer expectedResponseCode = 202;

    private String endpointURL;
    private String accessToken;

    public Client(String accessToken) {
        this.accessToken = accessToken;
        this.endpointURL = this.defaultEndpointURL;
    }

    public Client(String accessToken, String endpointURL) {
        this.accessToken = accessToken;
        this.endpointURL = endpointURL;
    }

    public String getEndpointURL() {
        return this.endpointURL;
    }

    public void push(Event event) throws
        UnexpectedResponseCode,
        java.net.MalformedURLException,
        java.net.ProtocolException,
        java.io.IOException {

        URL url = new URL(this.endpointURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", "LittleBlueFox java client");
        conn.setRequestProperty("Authorization", "Bearer: " + this.accessToken);

        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(event.toJSON());
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();

        if (responseCode != this.expectedResponseCode) {
            throw new UnexpectedResponseCode(this.expectedResponseCode, responseCode);
        }
    }
}
