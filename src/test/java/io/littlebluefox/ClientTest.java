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
    public void shouldSendEvent()
    {
        // TODO
    }
}
