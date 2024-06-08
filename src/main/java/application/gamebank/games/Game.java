package application.gamebank.games;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Comparator;

@SuppressWarnings("serial")
public class Game implements Comparator<Game>, Serializable {

	private String name;
	private String imageURL;
	private Image image;

	public Game() {
		this("");
	}
	Game(String name) {
		this(name,null);
	}
    public Game(String name, String imageURL) {
		super();
		this.name = name;
		this.imageURL = imageURL;
		this.image = new Image(imageURL);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
		this.image = new Image(imageURL);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
    public String toString() {
        return name;
    }

	@Override
	public int compare(Game o1, Game o2) {
		return o1.name.compareTo(o2.name);
	}
}
