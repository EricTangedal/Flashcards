package edu.miracosta.cs210.cs210finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FlashcardController implements Initializable {
    @FXML
    Label deckNameLabel, questionLabel, answerLabel, cardNumberLabel;

    private int currentCardNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deckNameLabel.setText(ApplicationManager.getInstance().getSelectedDeck().getDeckName());
        ApplicationManager.getInstance().setSelectedFlashcard(ApplicationManager.getInstance().getSelectedDeck().getFlashcards().getFirst());
        questionLabel.setText(ApplicationManager.getInstance().getSelectedFlashcard().getQuestion());
        currentCardNumber = 1;
        cardNumberLabel.setText("Card " + currentCardNumber + "/" + ApplicationManager.getInstance().getSelectedDeck().getFlashcards().size());
    }

    @FXML
    private void home() {
        try {
            Parent homeFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
            Scene homeScene = new Scene(homeFXML);

            ApplicationManager.getInstance().getStage().setScene(homeScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showAnswer() {
        answerLabel.setText(ApplicationManager.getInstance().getSelectedFlashcard().getAnswer());
    }

    @FXML
    private void nextFlashcard() {
        ApplicationManager.getInstance().selectNextFlashcard();
        questionLabel.setText(ApplicationManager.getInstance().getSelectedFlashcard().getQuestion());
        answerLabel.setText("");
        if(currentCardNumber == ApplicationManager.getInstance().getSelectedDeck().getFlashcards().size()){
            currentCardNumber = 1;
        }
        else {
            currentCardNumber++;
        }
        cardNumberLabel.setText("Card " + currentCardNumber + "/" + ApplicationManager.getInstance().getSelectedDeck().getFlashcards().size());
    }

    @FXML
    private void previousFlashcard() {
        ApplicationManager.getInstance().selectPreviousFlashcard();
        questionLabel.setText(ApplicationManager.getInstance().getSelectedFlashcard().getQuestion());
        answerLabel.setText("");
        if(currentCardNumber == 1){
            currentCardNumber = ApplicationManager.getInstance().getSelectedDeck().getFlashcards().size();
        }
        else {
            currentCardNumber--;
        }
        cardNumberLabel.setText("Card " + currentCardNumber + "/" + ApplicationManager.getInstance().getSelectedDeck().getFlashcards().size());
    }
}
