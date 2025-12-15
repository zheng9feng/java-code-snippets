package org.example;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.Assert.assertEquals;

public class Base64Test {

    @Test
    public void testEncodeBase64() {
        String originalString = "Hello Base64!";

        // --- Encoding ---
        // Get bytes from the string (UTF-8 is standard)
        byte[] bytesToEncode = originalString.getBytes(StandardCharsets.UTF_8);

        // Get the Basic Encoder and encode the bytes to a String
        String encodedString = Base64.getEncoder().encodeToString(bytesToEncode);
        assertEquals("SGVsbG8gQmFzZTY0IQ==", encodedString);
    }

    @Test
    public void testDecodeBase64() {
        String encodedString = "SGVsbG8gQmFzZTY0IQ==";
        // --- Decoding ---
        // Get the Basic Decoder and decode the String to bytes
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);
        assertEquals("Hello Base64!", decodedString);
    }
}
