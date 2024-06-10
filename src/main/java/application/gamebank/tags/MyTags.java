package application.gamebank.tags;


import java.util.LinkedList;
import java.util.List;

/** Classe permettant d'enregistrer TOUT les tags en même temps */
public class MyTags {

    private List<Tag> tags;

    public MyTags() {
        super();
        this.tags = new LinkedList<>();
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void delTag(Tag tag) {
        tags.remove(tag);
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Tag t: tags) {
            out.append(t).append(", ");
        }
        return out.toString();
    }
}
