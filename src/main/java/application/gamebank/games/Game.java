package application.gamebank.games;

import java.io.Serializable;
import java.util.Comparator;

public class Game implements Comparable<Game>, Serializable {

    private String name;
    private String imageURL;
    private double rating;
    private String releaseDate;

    public Game(String name, String imageURL, double rating, String releaseDate){
        super();
        this.name = name;
        this.imageURL = imageURL;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }
    public double getRating() {return rating;}
    public String getReleaseDate() {return releaseDate;}
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Game other) {
        return this.name.compareToIgnoreCase(other.name);
    }


}
