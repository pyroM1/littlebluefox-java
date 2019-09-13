package io.littlebluefox;

// import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;

public class Client {
    public static final String defaultEndpointURL = "https://events.littlebluefox.io/";
    public static final int expectedResponseCode = 202;
    public static final int defaultConnectTimeout = 8000;
    public static final int defaultReadTimeout = 8000;

    private String endpointURL;
    private String accessToken;
    private int connectTimeout;
    private int readTimeout;
    private Proxy proxy;
    private String proxyLogin;
    private String proxyPass;

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

    /**
     * Read timeout value in milliseconds.
     * 
     * @param value
     */
    public void setReadTimeout(int value) {
        this.readTimeout = value;
    }
  
    /**
     * Connect timeout value in milliseconds.
     * 
     * @param value
     */
    public void setConnectTimeout(int value) {
        this.connectTimeout = value;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public String getEndpointURL() {
        return this.endpointURL;
    }

    public void setProxyCredencial(String proxyLogin, String proxyPass) {
        this.proxyLogin = proxyLogin;
        this.proxyPass = proxyPass;
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
            if (proxyLogin != null && proxyPass != null) {
                Authenticator.setDefault(new Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(proxyLogin, proxyPass.toCharArray());
                    }
                });
            }
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
