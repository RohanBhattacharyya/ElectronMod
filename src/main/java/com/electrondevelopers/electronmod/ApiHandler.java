package com.eletrondevelopers.electronmod;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ApiHandler {
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent";
    private static final String API_KEY = "AIzaSyDd4tLO8-8lsoLk3ps4sxMnt2UxM1HTjgg"; // Replace with your actual API key


    public static void handleResponse(String responseBody) {
        Gson gson = new Gson();
        try {
            JsonObject responseJson = gson.fromJson(responseBody, JsonObject.class);
            String name = responseJson.get("name").getAsString();
            String description = responseJson.get("description").getAsString();
            String nbtData = responseJson.get("nbt").getAsString();
            boolean isBlock = responseJson.get("isblock").getAsBoolean();
            boolean isTool = responseJson.get("istool").getAsBoolean();

            // Now you can use these to create items or blocks
            if (isBlock) {
                // Call method to create block
            } else {
                // Call method to create item
            }
        } catch (Exception e) {
            System.out.println("Failed to parse JSON: " + e.getMessage());
        }
    }

    public static void sendPrompt(String craftingPrompt) {
        String basePrompt = "I will give you a nine item array, and this is using square brackets. This is a minecraft crafting grid array, where the top left of the crafting grid is the first item in the array, the top middle is the second item, the top right is the third, the middle left is the fourth, the middle middle is the fifth, the middle right is the sixth, the bottom left is the 7th, the bottom middle is the 8th, and the bottom right is the 9th and last item in the array. I will provide the item using the name of the mod it came from plus its name, for example, minecraft:dirt is a minecraft dirt block, and mekanism:steel_ingot, is a steel ingot from the mekanism mod. If a spot is empty in the grid, it will be represented by a '0' in the array. After I give you the array, please give back information about what you think it should craft, in the form {name:'string',description:'string',nbt:'string',isblock:bool,istool:bool}. This is because it will be a generated crafting recipe, and it should make sense based on what the player is putting in. Make sure that you invent something with this crafting recipe. IT IS OF UTMOST IMPORTANCE THAT YOU DO NOT DEVIATE FROM THIS FORMAT FOR ANY REASON. Only respond in this format, {name:'string',description:'string',nbt:'string',isblock:bool,istool:bool}, filling in the parts where you add what the item should be. DO NOT CHANGE WHAT THE FORMAT IS, AND DO NOT WRITE ANYTHING EXCEPT FOR THIS. DO NOT EXPLAIN ANYTHING, SIMPLY RESPOND IN THIS ARRAY.";
        String fullPrompt = String.format("%s Here is a recipe that you must invent: %s", basePrompt, craftingPrompt);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + "?key=" + API_KEY))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"contents\": [{\"parts\": [{\"text\": \"" + fullPrompt + "\"}]}]}"))
            .build();

        CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(request, BodyHandlers.ofString());
        handleFutureResponse(futureResponse);
    }

    private static void handleFutureResponse(CompletableFuture<HttpResponse<String>> futureResponse) {
        Executor executor = Executors.newSingleThreadExecutor();
        futureResponse.thenAcceptAsync(response -> {
            System.out.println("Response from Gemini API: " + response.body());
            // Here, parse the JSON response and handle it accordingly
        }, executor).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }
}
