package edu.miracosta.cs210.cs210finalproject;
import java.io.Serializable;
import java.util.LinkedList;

public class Deck implements Serializable {
    private String deckName;

    private LinkedList<Flashcard> flashcards;

    public Deck(String deckName) {
        this.deckName = deckName;
        this.flashcards = new LinkedList<>();
    }

    public String getDeckName(){
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public LinkedList<Flashcard> getFlashcards(){
        return flashcards;
    }

    public void addFlashcard(Flashcard flashcard){
        flashcards.add(flashcard);
    }

    public void deleteFlashcard(Flashcard flashcard){
        flashcards.remove(flashcard);
    }

    public String toString(){
        return deckName + "deck";
    }
}
