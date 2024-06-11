package application.gamebank.games;

import application.gamebank.Main;
import application.gamebank.tags.Tag;
import javafx.scene.image.Image;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Game implements Comparable<Game>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private transient Image image; // tansient -> pas enregistré
    private final String imageURL;
    private final double rating;
    private final String releaseDate;
    private List<Tag> tags;

    public Game(String name, String imageURL, double rating, String releaseDate) {
        this.name = name;
        this.imageURL = imageURL;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.tags = new LinkedList<>();
        setImage(imageURL);
    }

    // ===== TEST ===== //

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void delTag(Tag tag) {
        tags.remove(tag);
    }

    public List<Tag> getAllTags() {
        return tags;
    }

    // ===== END-TEST ===== //

    private void setImage(String imageURL) {
        try {
            this.image = new Image(imageURL);
        } catch (Exception e) {
            this.image = new Image(String.valueOf(Main.class.getResource("images/mosaique.png")));
        }
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        if (image == null && imageURL != null) {
            setImage(imageURL); // Recreate the image if it was not serialized
        }
        return image;
    }

    public String getImageURL() {
        return imageURL;
    }

    public double getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

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
        return Double.compare(rating, game.rating) == 0 &&
                Objects.equals(name, game.name) &&
                Objects.equals(releaseDate, game.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, releaseDate);
    }
}
