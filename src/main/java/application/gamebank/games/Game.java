package application.gamebank.games;

import java.io.Serializable;
import java.util.Comparator;

public class Game implements Comparable<Game>, Serializable {

    private String name;
    private String imageURL;
    private String description;
    public Game(String name, String imageURL, String description){
        super();
        ((name.charAt(0))+"").toUpperCase();
        this.name = name;
        this.imageURL = imageURL;
        this.description = description;
    }

    public Game(String name, String imageURL) {
        this(name, imageURL,"");
    }

    public Game() {
        this("", null);
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Game other) {
        return this.name.compareToIgnoreCase(other.name);
    }
}
