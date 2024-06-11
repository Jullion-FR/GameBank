package application.gamebank.tags;

import application.gamebank.games.Game;

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
        String txt = "";
        for (Tag t : tags) {
            txt += t.toString() + ", ";
        }
        return txt;
    }

    public Tag getTagByName(String name) {
        for (Tag tag : tags) {
            System.out.println(":"+tag + "==" + name+":");
            if (tag.toString().equals(name)) {
                return tag;
            }
        }
        return null;
    }

    /*public void delTag(Tag tag) {
        tag.delGame(game);
        tags.remove(tag);

    }*/

    public void delTag(Tag tag) {
        // Parcourir tous les jeux rattachés à ce tag
        for (Game game : new ArrayList<>(tag.games)) {
            // Supprimer le tag du jeu
            game.removeTag(tag);
            // Supprimer le jeu de la liste des jeux rattachés au tag
            tag.delGame(game);
        }

        // Finalement, supprimer le tag de la liste des tags
        tags.remove(tag);
    }

}
