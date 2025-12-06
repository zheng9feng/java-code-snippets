package org.example;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ApacheHttpClient5Test {

    @Test
    public void testGet() throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get("https://postman-echo.com/get")
                    .build();
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            httpclient.execute(httpGet, response -> {
                assertEquals(200, response.getCode());
                assertEquals("OK", response.getReasonPhrase());
                final HttpEntity entity = response.getEntity();
                String responseBody = EntityUtils.toString(entity);
                assertNotNull("empty response", responseBody);
                assertTrue("response does not contain expected url",
                        responseBody.contains("\"url\":\"https://postman-echo.com/get\""));
                // ensure the response body is fully consumed
                EntityUtils.consume(entity);

                return null;
            });
        }
    }

    @Test
    public void testPost() throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpPost = ClassicRequestBuilder.post("https://postman-echo.com/post")
                    .setEntity("{\"name\": \"John Doe\", \"age\": 30}")
                    .build();

            httpclient.execute(httpPost, response -> {
                assertEquals(200, response.getCode());
                assertEquals("OK", response.getReasonPhrase());
                final HttpEntity entity = response.getEntity();
                String responseBody = EntityUtils.toString(entity);
                assertNotNull("empty response", responseBody);
                assertTrue("response does not contain expected data",
                        responseBody.contains("\"url\":\"https://postman-echo.com/post\""));
                EntityUtils.consume(entity);

                return null;
            });
        }
    }
}
