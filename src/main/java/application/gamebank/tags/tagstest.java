package application.gamebank.tags;

import java.util.ArrayList;
import java.util.List;

public class tagstest {
    public static void main(String[] args) {
        List<tag> tags = new ArrayList<>();
        tags.add(new tag("beta"));
        tags.add(new tag("alpha"));
        tags.add(new tag("gamma"));

        listeTags listeTags = new listeTags(tags);
        listeTags.triAlpha();

        for (tag tag : listeTags.getListe()) {
            System.out.println(tag.getNom()); // should print alpha, beta, gamma
        }
    }
}
