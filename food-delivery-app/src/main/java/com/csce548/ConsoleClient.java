package com.csce548;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ConsoleClient
 *
 * Automated tester for the Spring Boot REST services.
 *
 * How to run:
 * 1. Start Spring Boot application FIRST:
 *      mvn spring-boot:run
 *
 * 2. Verify server is running:
 *      http://localhost:8080/users
 *
 * 3. Then run this class.
 *
 * This client will:
 *  - Create records
 *  - Retrieve all records
 *  - Update records
 *  - Delete records
 *
 * This demonstrates full functionality for Project 2.
 */
public class ConsoleClient {

    private static final String USER_URL = "http://localhost:8080/users";
    private static final String RESTAURANT_URL = "http://localhost:8080/restaurants";
    private static final String MENU_URL = "http://localhost:8080/menu";
    private static final String ORDER_URL = "http://localhost:8080/orders";

    public static void main(String[] args) throws Exception {

        System.out.println("=== Starting Automated System Test ===");

        // ======================
        // USER TEST
        // ======================
        System.out.println("\n--- USER TEST ---");

        sendRequest("POST", USER_URL,
                "{\"name\":\"Test User\",\"email\":\"test@test.com\",\"address\":\"123 Main\"}");

        sendRequest("GET", USER_URL, null);

        sendRequest("PUT", USER_URL + "/1",
                "{\"userId\":1,\"name\":\"Updated User\",\"email\":\"updated@test.com\",\"address\":\"456 Lane\"}");

        sendRequest("GET", USER_URL + "/1", null);

        sendRequest("DELETE", USER_URL + "/1", null);

        sendRequest("GET", USER_URL, null);


        // ======================
        // RESTAURANT TEST
        // ======================
        System.out.println("\n--- RESTAURANT TEST ---");

        sendRequest("POST", RESTAURANT_URL,
                "{\"name\":\"Test Restaurant\",\"address\":\"Downtown\",\"active\":true}");

        sendRequest("GET", RESTAURANT_URL, null);

        sendRequest("PUT", RESTAURANT_URL + "/1",
                "{\"restaurantId\":1,\"name\":\"Updated Restaurant\",\"address\":\"New Address\",\"active\":true}");

        sendRequest("GET", RESTAURANT_URL, null);

        sendRequest("DELETE", RESTAURANT_URL + "/1", null);

        sendRequest("GET", RESTAURANT_URL, null);


        // ======================
        // MENU ITEM TEST
        // Requires restaurant_id = 1 (or existing)
        // ======================
        System.out.println("\n--- MENU ITEM TEST ---");

        sendRequest("POST", MENU_URL,
                "{\"restaurantId\":1,\"name\":\"Burger\",\"price\":9.99}");

        sendRequest("GET", MENU_URL, null); // getAll()

        sendRequest("PUT", MENU_URL + "/1",
                "{\"menuItemId\":1,\"restaurantId\":1,\"name\":\"Cheeseburger\",\"price\":10.99}");

        sendRequest("GET", MENU_URL, null);

        sendRequest("DELETE", MENU_URL + "/1", null);

        sendRequest("GET", MENU_URL, null);


        // ======================
        // ORDER TEST
        // Requires user_id and restaurant_id to exist
        // ======================
        System.out.println("\n--- ORDER TEST ---");

        sendRequest("POST", ORDER_URL,
                "{\"userId\":1,\"restaurantId\":1,\"status\":\"Pending\",\"totalAmount\":25.50}");

        sendRequest("GET", ORDER_URL, null); // getAll()

        sendRequest("PUT", ORDER_URL + "/1/status", "\"Completed\"");

        sendRequest("GET", ORDER_URL, null);

        sendRequest("DELETE", ORDER_URL + "/1", null);

        sendRequest("GET", ORDER_URL, null);


        System.out.println("\n=== Automated Test Complete ===");
    }


    /**
     * Sends HTTP request and prints response.
     */
    private static void sendRequest(String method, String urlStr, String jsonBody) throws Exception {

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");

        // Only enable output if there is a body
        if (jsonBody != null) {
            conn.setDoOutput(true);
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