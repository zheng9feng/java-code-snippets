package org.example;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.UrlBase64;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Base64 encoding and decoding using Bouncy Castle.
 */
public class Base64ByBCTest {

    @Test
    public void encodeBase64Test() {
        assertEquals("SGVsbG8gQmFzZTY0IQ==", new String(Base64.encode("Hello Base64!".getBytes())));
    }

    @Test
    public void decodeBase64Test() {
        assertEquals("Hello Base64!", new String(Base64.decode("SGVsbG8gQmFzZTY0IQ==")));
    }

    @Test
    public void encodeUrlSafeBase64Test() {
        String originalURL = "https://example.com/?q=Base64UrlSafe-Example!";
        assertEquals("aHR0cHM6Ly9leGFtcGxlLmNvbS8_cT1CYXNlNjRVcmxTYWZlLUV4YW1wbGUh",
                new String(UrlBase64.encode(originalURL.getBytes())));
    }

    @Test
    public void decodeUrlSafeBase64Test() {
        String originalURL = "https://example.com/?q=Base64UrlSafe-Example!";
        assertEquals(originalURL,
                new String(UrlBase64.decode("aHR0cHM6Ly9leGFtcGxlLmNvbS8_cT1CYXNlNjRVcmxTYWZlLUV4YW1wbGUh")));
    }
}
