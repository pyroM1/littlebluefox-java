# littlebluefox-java


## Usage


```java
    import io.littlebluefox.Client;
    import io.littlebluefox.Event;
    // ...

    // ...
    String accessToken = "<your-access-token>";
    Client client = new Client(accessToken, "https://<littlebluefox-endpoint-url>");
    this.lbfClient = client;
    // ...

    // ...
    String eventType = "authentication_success";
    String uRef = "123";
    String email = "demo@demo.com";
    String remoteIP = "35.180.72.95";
    Map<String, String> httpHeaders = new HashMap<String, String>();
    httpHeaders.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0.3 Safari/605.1.15");

    Event event = new Event(eventType, uRef, email, remoteIP, httpHeaders);

    this.lbfClient.push(event);
    // ...
```


### Development

```
# build the project
$ mvn package
```

```
# clean
$ mvn clean dependency:copy-dependencies package
```


