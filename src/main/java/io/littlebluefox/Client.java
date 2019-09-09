package io.littlebluefox;

// import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.Proxy;
import org.json.simple.JSONObject;

public class Client {
    public static final String defaultEndpointURL = "https://events.littlebluefox.io/";
    public static final Integer expectedResponseCode = 202;
    public static final Integer defaultConnectTimeout = 8000;
    public static final Integer defaultReadTimeout = 8000;

    private String endpointURL;
    private String accessToken;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Proxy proxy;

    public Client(String accessToken) {
        this.accessToken = accessToken;
        this.endpointURL = this.defaultEndpointURL;
        this.connectTimeout = this.defaultConnectTimeout;
        this.readTimeout = this.defaultReadTimeout;
    }

    public Client(String accessToken, String endpointURL) {
        this.accessToken = accessToken;
        this.endpointURL = endpointURL;
        this.connectTimeout = this.defaultConnectTimeout;
        this.readTimeout = this.defaultReadTimeout;
    }

    public void setReadTimeout(Integer value) {
        this.readTimeout = value;
    }

    public void setConnectTimeout(Integer value) {
        this.connectTimeout = value;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
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

        HttpURLConnection conn;

        if (this.proxy == null) {
            conn = (HttpURLConnection) url.openConnection();
        } else {
            conn = (HttpURLConnection) url.openConnection(this.proxy);
        }

        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", "LittleBlueFox java client");
        conn.setRequestProperty("Authorization", "Bearer " + this.accessToken);

        conn.setReadTimeout(this.readTimeout);
        conn.setConnectTimeout(this.connectTimeout);

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
