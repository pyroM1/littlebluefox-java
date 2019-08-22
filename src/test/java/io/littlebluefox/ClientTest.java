package io.littlebluefox;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Map;
import java.util.HashMap;

public class ClientTest 
{
    @Test
    public void shouldBeInitialized()
    {
        String accessToken = "access-token-code";

        Client client = new Client(accessToken);
        assertEquals(Client.defaultEndpointURL, client.getEndpointURL());

        String endpointURL = "https://demo.littlebluefox.io/";
        client = new Client(accessToken, endpointURL);
        assertEquals(endpointURL, client.getEndpointURL());
    }

    @Test
    public void shouldSendEvent() throws 
        UnexpectedResponseCode,
        java.net.MalformedURLException,
        java.net.ProtocolException,
        java.io.IOException
    {
        String eventType = "authentication_success";
        String uRef = "123";
        String email = "demo@demo.com";
        String remoteIP = "35.180.72.95";
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0.3 Safari/605.1.15";
        Map<String, String> httpHeaders = new HashMap<String, String>();
        httpHeaders.put("User-Agent", userAgent);
        Event event = new Event(eventType, uRef, email, remoteIP, httpHeaders);

        String accessToken = "<fake-access-token>";
        Client client = new Client(accessToken, "http://127.0.0.1:8080/");
        client.push(event);
    }
}
