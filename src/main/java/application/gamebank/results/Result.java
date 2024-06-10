package application.gamebank.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
    private String name;
    @JsonProperty("background_image")
    private String backgroundImage;
    @JsonProperty("released")
    private String releaseDate;
    private double rating;

    public String getReleaseDate() {return releaseDate;}
    public double getRating() {return rating;}
    public String getName() { return name; }
    public String getBackgroundImage() { return backgroundImage; }

    
}
