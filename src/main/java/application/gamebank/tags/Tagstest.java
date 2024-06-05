package application.gamebank.tags;

import java.util.ArrayList;
import java.util.List;

public class Tagstest {
    public static void main(String[] args) {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("beta"));
        tags.add(new Tag("alpha"));
        tags.add(new Tag("gamma"));

        ListeTags listeTags = new ListeTags(tags);
        listeTags.triAlpha();

        for (Tag tag : listeTags.getListe()) {
            System.out.println(tag.getNom()); // should print alpha, beta, gamma
        }
    }
}
