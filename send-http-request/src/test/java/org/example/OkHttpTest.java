package org.example;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class OkHttpTest {

    @Test
    public void testGet() throws IOException {
        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://postman-echo.com/get")
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertTrue("response failed!", response.isSuccessful());
            // Headers responseHeaders = response.headers();
            // for (int i = 0; i < responseHeaders.size(); i++) {
            //     System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            // }
            ResponseBody responseBody = response.body();
            assertNotNull("empty response", responseBody);
            assertTrue("response does not contain expected url",
                    responseBody.string().contains("\"url\":\"https://postman-echo.com/get\""));
        }
    }

    @Test
    public void testPost() throws IOException {
        final OkHttpClient client = new OkHttpClient();

        MediaType JSON = MediaType.get("application/json");
        RequestBody body = RequestBody.create("{\"name\": \"John Doe\", \"age\": 30}", JSON);
        Request request = new Request.Builder()
                .url("https://postman-echo.com/post")
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertTrue("response failed!", response.isSuccessful());
            // Headers responseHeaders = response.headers();
            // for (int i = 0; i < responseHeaders.size(); i++) {
            //     System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            // }
            ResponseBody responseBody = response.body();
            assertNotNull("empty response", responseBody);
            assertTrue("response does not contain expected data",
                    responseBody.string().contains("\"url\":\"https://postman-echo.com/post\""));
        }
    }
}
