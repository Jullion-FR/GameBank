package application.gamebank.api;

import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import application.gamebank.results.Result;
import application.gamebank.results.ResultGame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class APIManager {

    public void setInformations(MyGames games, String searchedText, int wanted) throws GameNotFoundException {

        String searchedEncoded = "";
        searchedEncoded = URLEncoder.encode(searchedText, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                //  https://rawg-video-games-database.p.rapidapi.com/games
                .uri(URI.create("https://api.rawg.io/api/games" + "?key=41a5c4ca050840b18caf582c213f33a5" + "&search=" + searchedEncoded + "&search_exact=1" + "&page_size=" + wanted)).method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        ResultGame result;
        try {
            result = objectMapper.readValue(response.body(), ResultGame.class);
            if (result.getResults().length == 0) {
                throw new GameNotFoundException();
            }

            for (int i = 0; i < wanted; i++) {
                try {
                    Result gameResult = result.getResults()[i];
                    // games.addGame(new Game(gameResult.getName(), gameResult.getBackgroundImage()));
                } catch (Exception e) {
                }
            }

        } catch (JsonProcessingException | GameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** Renvoie la liste des jeux trouver */
    public static List<Game> searchQuery(String request) {
        return new LinkedList<>();
    }

}
