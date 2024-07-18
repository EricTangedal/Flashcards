package edu.miracosta.cs210.cs210finalproject;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class ApplicationManager {

    //region ApplicationManager

    private static ApplicationManager instance;

    private ApplicationManager() {}

    public static ApplicationManager getInstance() {
        if (instance == null) {
            instance = new ApplicationManager();
        }
        return instance;
    }

    protected static void showSaveConfirmationDialog(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Save changes?");
        ButtonType buttonTypeSave = new ButtonType("Save and Exit");
        ButtonType buttonTypeDontSave = new ButtonType("Exit WITHOUT Saving");
        ButtonType buttonTypeCancel = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(buttonTypeSave, buttonTypeDontSave, buttonTypeCancel);
        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeSave) {
                save(instance.getDecks());
                stage.close();
            } else if (type == buttonTypeDontSave) {
                stage.close();
            }
        });
    }

    //endregion

    //region StageManager

    private static Stage stage;

    public void setStage(Stage stage) {
        ApplicationManager.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    //endregion

    //region DeckManager

    private LinkedList<Deck> decks = new LinkedList<>();
    private Deck selectedDeck;

    public LinkedList<Deck> getDecks() {
        return decks;
    }

    public void setDecks(LinkedList<Deck> decks) {
        this.decks = decks;
    }

    public void addDeck(Deck deck){
        if(!decks.contains(deck)) {
            decks.add(deck);
        }
    }

    public void deleteDeck(Deck deck){
        decks.remove(deck);
    }

    public void setSelectedDeck(Deck deck){
        selectedDeck = deck;
    }

    public Deck getSelectedDeck(){
        return selectedDeck;
    }

    public Deck findDeckByName(String name) {
        for (Deck deck : decks) {
            if (deck.getDeckName().equals(name)) {
                return deck;
            }
        }
        return null;
    }

    //endregion

    //region DeckEditor

    private Flashcard selectedFlashcard;

    public void setSelectedFlashcard(Flashcard flashcard){
        selectedFlashcard = flashcard;
    }

    public Flashcard getSelectedFlashcard(){
        return selectedFlashcard;
    }

    public Flashcard findFlashcardInDeckByQuestion(String question) {
        for (Flashcard flashcard : getSelectedDeck().getFlashcards()) {
            if (flashcard.getQuestion().equals(question)) {
                return flashcard;
            }
        }
        return null;
    }

    //endregion

    //region Flashcard

    public int getFlashcardIndexByQuestion(String question) {
        for (Flashcard flashcard : getSelectedDeck().getFlashcards()) {
            if (flashcard.getQuestion().equals(question)) {
                return getSelectedDeck().getFlashcards().indexOf(flashcard);
            }
        }
        return 0;
    }

    public void selectNextFlashcard() {
        if(getFlashcardIndexByQuestion(getSelectedFlashcard().getQuestion()) == getSelectedDeck().getFlashcards().size() - 1){
            setSelectedFlashcard(getSelectedDeck().getFlashcards().getFirst());
        }
        else {
            setSelectedFlashcard(getSelectedDeck().getFlashcards().get(getFlashcardIndexByQuestion(getSelectedFlashcard().getQuestion())+1));
        }
    }

    public void selectPreviousFlashcard() {
        if(getFlashcardIndexByQuestion(getSelectedFlashcard().getQuestion()) == 0){
            setSelectedFlashcard(getSelectedDeck().getFlashcards().getLast());
        }
        else {
            setSelectedFlashcard(getSelectedDeck().getFlashcards().get(getFlashcardIndexByQuestion(getSelectedFlashcard().getQuestion())-1));
        }
    }

    //endregion

    //region Serialization

    public static void save(LinkedList<Deck> decks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir") + "\\data.txt"))) {
            oos.writeObject(decks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<Deck> load() {
        if (Files.exists(Path.of(System.getProperty("user.dir") + "\\data.txt"))){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir") + "\\data.txt"))) {
                return (LinkedList<Deck>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return new LinkedList<>();
            }
        }
        else {
            return new LinkedList<>();
        }
    }

    //endregion
}