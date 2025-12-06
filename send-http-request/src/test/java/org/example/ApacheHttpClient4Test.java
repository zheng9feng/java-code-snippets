package org.example;

import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ApacheHttpClient4Test {

    @Test
    public void testGet() throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("https://postman-echo.com/get");
            request.setHeader("Content-Type", "application/json");

            try (CloseableHttpResponse response = client.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = response.getEntity() != null ? EntityUtils.toString(response.getEntity()) : null;

                assertEquals(200, statusCode);
                assertNotNull("empty response", responseBody);
                assertTrue("response does not contain expected url",
                        responseBody.contains("\"url\":\"https://postman-echo.com/get\""));
            }
        }
    }

    @Test
    public void testPost() throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost("https://postman-echo.com/post");
            request.setHeader("Content-Type", "application/json");
            String json = "{\"name\": \"John Doe\", \"age\": 30}";
            request.setEntity(new StringEntity(json));

            try (CloseableHttpResponse response = client.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = response.getEntity() != null ? EntityUtils.toString(response.getEntity()) : null;

                assertEquals(200, statusCode);
                assertNotNull("empty response", responseBody);
                assertTrue("response does not contain expected data",
                        responseBody.contains("\"name\":\"John Doe\""));
            }
        }
    }

    @Test
    public void testPut() throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut request = new HttpPut("https://postman-echo.com/put");
            request.setHeader("Content-Type", "application/json");
            String json = "{\"name\": \"John Doe\", \"age\": 30}";
            request.setEntity(new StringEntity(json));

            try (CloseableHttpResponse response = client.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = response.getEntity() != null ? EntityUtils.toString(response.getEntity()) : null;

                assertEquals(200, statusCode);
                assertNotNull("empty response", responseBody);
                assertTrue("response does not contain expected data",
                        responseBody.contains("\"name\":\"John Doe\""));
            }
        }
    }

    @Test
    public void testDelete() throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete request = new HttpDelete("https://postman-echo.com/delete?messageId=12345&name=JohnDoe");
            request.setHeader("Content-Type", "application/json");
            // Note: HttpDelete does not support a request body in Apache HttpClient 4.x
            try (CloseableHttpResponse response = client.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = response.getEntity() != null ? EntityUtils.toString(response.getEntity()) : null;

                assertEquals(200, statusCode);
                assertNotNull("empty response", responseBody);
                assertTrue("response does not contain expected url",
                        responseBody.contains("\"url\":\"https://postman-echo.com/delete?messageId=12345&name=JohnDoe\""));
            }
        }
    }
}
