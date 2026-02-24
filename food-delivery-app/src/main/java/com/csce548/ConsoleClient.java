package com.csce548;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsoleClient {

    private static final String USER_URL = "http://localhost:8080/users";
    private static final String RESTAURANT_URL = "http://localhost:8080/restaurants";
    private static final String MENU_URL = "http://localhost:8080/menu";
    private static final String ORDER_URL = "http://localhost:8080/orders";

    public static void main(String[] args) throws Exception {

        System.out.println("=== Starting Automated System Test ===");

        // USER TEST
        sendRequest("POST", USER_URL,
                "{\"name\":\"Test User\",\"email\":\"test@test.com\",\"address\":\"123 Main\"}");

        sendRequest("GET", USER_URL, null);

        sendRequest("PUT", USER_URL + "/1",
                "{\"userId\":1,\"name\":\"Updated User\",\"email\":\"updated@test.com\",\"address\":\"456 Lane\"}");

        sendRequest("GET", USER_URL + "/1", null);

        sendRequest("DELETE", USER_URL + "/1", null);

        // RESTAURANT TEST
        sendRequest("POST", RESTAURANT_URL,
                "{\"name\":\"Pizza Place\",\"address\":\"Downtown\",\"active\":true}");

        sendRequest("GET", RESTAURANT_URL, null);

        System.out.println("=== Test Complete ===");
    }

    // ===== ADD THIS METHOD =====
    private static void sendRequest(String method, String urlStr, String jsonBody) throws Exception {

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Send body if present
        if (jsonBody != null) {
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonBody.getBytes());
                os.flush();
            }
        }

        int responseCode = conn.getResponseCode();
        System.out.println("\n" + method + " " + urlStr);
        System.out.println("Response Code: " + responseCode);

        BufferedReader reader;
        if (responseCode >= 200 && responseCode < 300) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
        conn.disconnect();
    }
}