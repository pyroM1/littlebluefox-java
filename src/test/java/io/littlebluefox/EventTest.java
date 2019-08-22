package io.littlebluefox;

import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest
{
    @Test
    public void shouldBeInitialized()
    {
        String eventType = "authentication_success";
        String uRef = "123";
        String email = "demo@demo.com";
        String remoteIP = "35.180.72.95";
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0.3 Safari/605.1.15";
        Map<String, String> httpHeaders = new HashMap<String, String>();
        httpHeaders.put("User-Agent", userAgent);

        Event event = new Event(eventType, uRef, email, remoteIP, httpHeaders);

        assertEquals(eventType, event.getEventType());
        assertEquals(uRef, event.getURef());
        assertEquals(email, event.getEmail());
        assertEquals(remoteIP, event.getRemoteIP());
        assertEquals(httpHeaders, event.getHTTPHeaders());
    }

    @Test
    public void shouldConvertToJSON() throws java.net.MalformedURLException
    {
        String eventType = "authentication_success";
        String uRef = "123";
        String email = "demo@demo.com";
        String remoteIP = "35.180.72.95";
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0.3 Safari/605.1.15";
        Map<String, String> httpHeaders = new HashMap<String, String>();
        httpHeaders.put("User-Agent", userAgent);

        Event event = new Event(eventType, uRef, email, remoteIP, httpHeaders);
        String want = "{\"event_type\":\"authentication_success\",\"remote_ip\":\"35.180.72.95\",\"http_headers\":{\"User-Agent\":\"Mozilla\\/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit\\/605.1.15 (KHTML, like Gecko) Version\\/12.0.3 Safari\\/605.1.15\"},\"uref\":\"123\",\"email\":\"demo@demo.com\"}";
        assertEquals(want, event.toJSON());
    }
}

