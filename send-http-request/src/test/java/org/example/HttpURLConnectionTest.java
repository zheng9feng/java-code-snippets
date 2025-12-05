package org.example;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpURLConnectionTest {

    @Test
    public void testHttpURLConnectionWithGet() throws IOException {
        // Create a neat value object to hold the URL
        URL url = new URL("https://postman-echo.com/get");

        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");

        handleResponse(connection);
    }

    @Test
    public void testHttpURLConnectionWithPost() throws IOException {
        // Create a neat value object to hold the URL
        URL url = new URL("https://postman-echo.com/post");

        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestMethod("POST");
        //  indicate that we will be sending data to the server.
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");

        String jsonInputString = "{\"name\": \"John Doe\", \"age\": 30}";

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        handleResponse(connection);
    }

    @Test
    public void testHttpURLConnectionWithPut() throws IOException {
        URL url = new URL("https://postman-echo.com/put");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");
        String jsonInputString = "{\"name\": \"John Doe\", \"age\": 30}";
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        handleResponse(connection);
    }

    @Test
    public void testHttpURLConnectionWithDelete() throws IOException {
        URL url = new URL("https://postman-echo.com/delete");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");
        String jsonInputString = "{\"name\": \"John Doe\", \"age\": 30}";
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        handleResponse(connection);
    }

    private static void handleResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                // Process the response string
                System.out.println(response);
            }
        } else {
            // Handle error response
            System.err.println("HTTP POST request failed with response code: " + responseCode);
        }

        connection.disconnect();
    }
}
