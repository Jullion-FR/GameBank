package application.gamebank.tags;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyTags implements Serializable {

    private final List<Tag> tags;

    public MyTags() {
        super();
        tags = new ArrayList<>();
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public List<Tag> getAllTags() {
        return tags;
    }

    public int nbTag() {
        return tags.size();
    }

    @Override
    public String toString() {
        for (Tag t : tags) {

        }
        return "";
    }

}
