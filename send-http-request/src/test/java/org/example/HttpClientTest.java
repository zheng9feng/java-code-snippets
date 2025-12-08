package org.example;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.*;

public class HttpClientTest {

    @Test
    public void testGet() throws IOException, InterruptedException {
        // create a client
        HttpClient client = HttpClient.newHttpClient();
        // create a request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://postman-echo.com/get"))
                .header("Content-Type", "application/json") // Add headers if needed
                .GET() // Or .POST(BodyPublishers.ofString("{\"key\": \"value\"}")) for a POST request with JSON body
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Access response details:
        int statusCode = response.statusCode();
        String responseBody = response.body();

        assertEquals(200, statusCode);
        assertNotNull("empty response", responseBody);
        assertTrue("response does not contain expected url",
                responseBody.contains("\"url\":\"https://postman-echo.com/get\""));
    }

    @Test
    public void testPost() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://postman-echo.com/post"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"name\": \"John Doe\", \"age\": 30}"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        String responseBody = response.body();

        assertEquals(200, statusCode);
        assertNotNull("empty response", responseBody);
        assertTrue("response does not contain expected data",
                responseBody.contains("\"url\":\"https://postman-echo.com/post\""));
    }
}
