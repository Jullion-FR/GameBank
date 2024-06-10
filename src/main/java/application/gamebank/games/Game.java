package application.gamebank.games;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Game implements Comparable<Game>, Serializable {

    private final String name;
    private final String imageURL;
    private final double rating;
    private final String releaseDate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Double.compare(rating, game.rating) == 0 && Objects.equals(name, game.name) && Objects.equals(releaseDate, game.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, releaseDate);
    }
}
