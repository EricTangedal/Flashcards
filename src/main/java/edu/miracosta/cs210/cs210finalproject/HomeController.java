package edu.miracosta.cs210.cs210finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    //test
    @FXML
    private VBox vBox;
    @FXML
    private TextField deckNameInput;
    @FXML
    private Label emptyLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDecks(ApplicationManager.getInstance().getDecks());
    }

    @FXML
    private void newDeck() {
        if(ApplicationManager.getInstance().findDeckByName(deckNameInput.getText()) == null){
            Deck deck = new Deck(deckNameInput.getText());
            ApplicationManager.getInstance().addDeck(deck);
            ApplicationManager.getInstance().setSelectedDeck(deck);
            try {
                Parent flashCardManagerFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DeckEditor.fxml")));
                Scene flashCardManagerScene = new Scene(flashCardManagerFXML);

                ApplicationManager.getInstance().getStage().setScene(flashCardManagerScene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void editDeck(){
        if(ApplicationManager.getInstance().getSelectedDeck() != null) {
            try {
                Parent flashcardFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DeckEditor.fxml")));
                Scene flashcardScene = new Scene(flashcardFXML);

                ApplicationManager.getInstance().getStage().setScene(flashcardScene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void deleteDeck(){
        if(ApplicationManager.getInstance().getSelectedDeck() != null) {
            ApplicationManager.getInstance().deleteDeck(ApplicationManager.getInstance().getSelectedDeck());
            vBox.getChildren().removeIf(node -> node instanceof Button && ((Button) node).getText().equals(ApplicationManager.getInstance().getSelectedDeck().getDeckName()));
        }
    }

    @FXML
    private void viewCards(){
        if(ApplicationManager.getInstance().getSelectedDeck() != null){
            if(!ApplicationManager.getInstance().getSelectedDeck().getFlashcards().isEmpty()){
                try {
                    Parent flashcardFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Flashcard.fxml")));
                    Scene flashcardScene = new Scene(flashcardFXML);

                    ApplicationManager.getInstance().getStage().setScene(flashcardScene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                emptyLabel.setText("Unable to open. The selected deck is empty!");
            }
        }
    }

    @FXML
    private void takeQuiz() {
        if(ApplicationManager.getInstance().getSelectedDeck() != null) {
            if (!ApplicationManager.getInstance().getSelectedDeck().getFlashcards().isEmpty()) {
                try {
                    Parent quizFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Quiz.fxml")));
                    Scene quizScene = new Scene(quizFXML);

                    ApplicationManager.getInstance().getStage().setScene(quizScene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void selectDeck(Button button) {
        Deck deck = ApplicationManager.getInstance().findDeckByName(button.getText());
        ApplicationManager.getInstance().setSelectedDeck(deck);
        emptyLabel.setText("");
    }

    private void loadDecks(LinkedList<Deck> decks) {
        for (Deck deck : decks) {
            Button button = new Button(deck.getDeckName());
            button.setPrefSize(362, 40);
            button.setFont(Font.font("Garamond", 18));
            button.setOnAction(event -> selectDeck(button));
            vBox.getChildren().add(button);
        }
    }

    @FXML
    private void exitApplication() {
        ApplicationManager.save(ApplicationManager.getInstance().getDecks());
        System.exit(0);
    }
}
