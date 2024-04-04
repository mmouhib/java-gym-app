package com.esprit.gui.utils.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodMacros {

    private String foodBarCode;

    public String requestData() throws Exception {

        String url = String.format("https://world.openfoodfacts.org/api/v2/product/%s.json", foodBarCode);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // System.out.println("Response Code: " + response.statusCode());
        return response.body();
    }

    public PlateResponse parseResponse() throws Exception {
        String name, imageUrl;
        int calories, carbs, fat, protein, sugar, salt, sodium;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(this.requestData()).get("product");
        JsonNode nutrimentsNode = rootNode.get("nutriments");

        name = rootNode.get("product_name").asText();

        try {
            calories = Integer.parseInt(nutrimentsNode.get("energy").asText());
        } catch (Exception e) {
            calories = 0;
        }
        try {
            carbs = Integer.parseInt(nutrimentsNode.get("carbohydrates").asText());
        } catch (Exception e) {
            carbs = 0;
        }
        try {
            fat = Integer.parseInt(nutrimentsNode.get("fat").asText());
        } catch (Exception e) {
            fat = 0;
        }
        try {
            protein = Integer.parseInt(nutrimentsNode.get("proteins").asText());
        } catch (Exception e) {
            protein = 0;
        }
        try {
            sugar = Integer.parseInt(nutrimentsNode.get("sugars").asText());
        } catch (Exception e) {
            sugar = 0;
        }
        try {
            sodium = Integer.parseInt(nutrimentsNode.get("sodium").asText());
        } catch (Exception e) {
            sodium = 0;
        }
        try {
            salt = Integer.parseInt(nutrimentsNode.get("salt").asText());
        } catch (Exception e) {
            salt = 0;
        }
        try {
            imageUrl = rootNode.get("image_front_url").asText();
        } catch (Exception e) {
            imageUrl = "";
        }


        return new PlateResponse(
                name,
                calories,
                protein,
                carbs,
                fat,
                sugar,
                salt,
                sodium,
                imageUrl
        );


    }

    public boolean requestIsValid() {
        String errorMessage = "no code or invalid code";
        return true;
    }
}