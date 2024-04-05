package com.esprit.gui.utils.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EmailValidator {

    public boolean verify(String emailToValidate) {

        //String emailToValidate = "te";
        String apiKey = "BmVoG5TB03mFaakQdOEmWjU7lcK4d8oG5ZWT";
        String apiUrl = "https://mailbite.io/api/check?key=" + apiKey + "&email=" + emailToValidate;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response
            // You may use libraries like Gson for parsing JSON
            System.out.println(response.toString());
            System.out.println(response.indexOf("email_status"));


            // Find the index of "email_status"
            int emailStatusIndex = response.indexOf("\"email_status\"");
            String emailStatus = "";
            // Check if "email_status" is found
            if (emailStatusIndex != -1) {
                // Find the starting index of the value of "email_status"
                int valueStartIndex = response.indexOf(":", emailStatusIndex) + 1;

                // Find the ending index of the value of "email_status"
                int valueEndIndex = response.indexOf(",", valueStartIndex);
                if (valueEndIndex == -1) {
                    // If the value is the last element in the JSON, find the ending index accordingly
                    valueEndIndex = response.indexOf("}", valueStartIndex);
                }

                // Extract the value of "email_status"
                emailStatus = response.substring(valueStartIndex + 1, valueEndIndex - 1).trim();

                // Print the value of "email_status"
                System.out.println("Email Status: " + emailStatus);
            }
            if (emailStatus.equals("VALID")) {
                return true;
            } else {
                return false;
            }

            // Further processing of the response
            // Extract the validation result and act accordingly

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
}



