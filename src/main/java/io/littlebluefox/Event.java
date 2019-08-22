package io.littlebluefox;

import java.util.Map;
import org.json.simple.JSONObject;

public class Event {
    private String eventType;
    private String uRef;
    private String email;
    private String remoteIP;
    private Map<String, String> httpHeaders;

    public Event(String eventType, String uRef, String email, String remoteIP,
            Map<String, String> httpHeaders ) {
        this.eventType = eventType;
        this.uRef = uRef;
        this.email = email;
        this.remoteIP = remoteIP;
        this.httpHeaders = httpHeaders;
    }

    public String getEventType() {
        return this.eventType;
    }

    public String getURef() {
        return this.uRef;
    }

    public String getEmail() {
        return this.email;
    }

    public String getRemoteIP() {
        return this.remoteIP;
    }

    public Map<String, String> getHTTPHeaders() {
        return this.httpHeaders;
    }

    public String toJSON() throws java.net.MalformedURLException {
        JSONObject obj = new JSONObject();

        obj.put("event_type", this.eventType);
        obj.put("uref", this.uRef);
        obj.put("email", this.email);
        obj.put("remote_ip", this.remoteIP);
        obj.put("http_headers", this.httpHeaders);

        return obj.toString();
    }
}
