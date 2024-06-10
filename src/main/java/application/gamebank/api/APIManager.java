package application.gamebank.api;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import application.gamebank.results.Result;
import application.gamebank.results.ResultGame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIManager {

    public MyGames setInformations( String searchedText, int wanted)  throws GameNotFoundException{
        MyGames games = new MyGames();
        String searchedEncoded = "";
        searchedEncoded = URLEncoder.encode(searchedText, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                //  https://rawg-video-games-database.p.rapidapi.com/games
                .uri(URI.create("https://api.rawg.io/api/games"
                        + "?key=41a5c4ca050840b18caf582c213f33a5"
                        + "&search=" + searchedEncoded
                        + "&search_exact=2"
                        + "&page_size="+wanted))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException|IOException e) {
            Thread.currentThread().interrupt();
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        ResultGame result;
        try {
            result = objectMapper.readValue(response.body(), ResultGame.class);
            if (result.getResults().length == 0) {
                throw new GameNotFoundException();
            }

            for (int i = 0; i < wanted ; i++) {
                try {
                    Result gameResult = result.getResults()[i];
                    games.addGame(new Game(
                            gameResult.getName(),
                            gameResult.getBackgroundImage(),
                            gameResult.getRating(),
                            gameResult.getReleaseDate()
                            )
                    );
                }catch(Exception e){}
            }

        } catch (JsonProcessingException e) {
            System.out.println("erreur json");
            e.printStackTrace();
            setInformations(searchedText, wanted);
        }
        return games;
    }

}
