package org.example;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

    @Test
    public void testEncodeUrlSafeBase64() {
        String originalString = "https://example.com/?q=Base64UrlSafe-Example!";

        byte[] bytesToEncode = originalString.getBytes(StandardCharsets.UTF_8);
        // --- URL Safe Encoding ---
        String encodedStringWithUrlEncoder = Base64.getUrlEncoder().encodeToString(bytesToEncode);
        String encodedStringWithNonUrlEncoder = Base64.getEncoder().encodeToString(bytesToEncode);

        assertEquals("aHR0cHM6Ly9leGFtcGxlLmNvbS8_cT1CYXNlNjRVcmxTYWZlLUV4YW1wbGUh",
                encodedStringWithUrlEncoder);
        assertEquals("aHR0cHM6Ly9leGFtcGxlLmNvbS8/cT1CYXNlNjRVcmxTYWZlLUV4YW1wbGUh",
                encodedStringWithNonUrlEncoder);
        assertNotEquals(encodedStringWithUrlEncoder, encodedStringWithNonUrlEncoder);
    }

    @Test
    public void testDecodeUrlSafeBase64() {
        String encodedStringWithUrlEncoder = "aHR0cHM6Ly9leGFtcGxlLmNvbS8_cT1CYXNlNjRVcmxTYWZlLUV4YW1wbGUh";
        String encodedStringWithNonUrlEncoder = "aHR0cHM6Ly9leGFtcGxlLmNvbS8/cT1CYXNlNjRVcmxTYWZlLUV4YW1wbGUh";
        // --- URL Safe Decoding ---
        byte[] decodedBytesFromUrlEncoder = Base64.getUrlDecoder().decode(encodedStringWithUrlEncoder);
        byte[] decodedBytesFromNonUrlEncoder = Base64.getDecoder().decode(encodedStringWithNonUrlEncoder);

        String decodedStringFromUrlEncoder = new String(decodedBytesFromUrlEncoder, StandardCharsets.UTF_8);
        String decodedStringFromNonUrlEncoder = new String(decodedBytesFromNonUrlEncoder, StandardCharsets.UTF_8);

        assertEquals("https://example.com/?q=Base64UrlSafe-Example!",
                decodedStringFromUrlEncoder);
        assertEquals("https://example.com/?q=Base64UrlSafe-Example!",
                decodedStringFromNonUrlEncoder);
    }
}
