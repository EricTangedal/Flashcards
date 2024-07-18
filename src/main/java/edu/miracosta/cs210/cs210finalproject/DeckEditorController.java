package edu.miracosta.cs210.cs210finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

public class DeckEditorController implements Initializable {
    @FXML
    VBox vBox;
    @FXML
    Label deckNameLabel;
    @FXML
    TextField flashcardQuestionInput, flashcardAnswerInput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deckNameLabel.setText(ApplicationManager.getInstance().getSelectedDeck().getDeckName() + " flashcards");
        loadFlashcards(ApplicationManager.getInstance().getSelectedDeck().getFlashcards());
    }

    @FXML
    private void selectFlashcard(Button button) {
        Flashcard flashcard = ApplicationManager.getInstance().findFlashcardInDeckByQuestion(button.getText());
        ApplicationManager.getInstance().setSelectedFlashcard(flashcard);
        flashcardQuestionInput.setText(flashcard.getQuestion());
        flashcardAnswerInput.setText(flashcard.getAnswer());
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
    private void newFlashcard() {
        if(ApplicationManager.getInstance().findFlashcardInDeckByQuestion(flashcardQuestionInput.getText()) == null){
            Flashcard flashcard = new Flashcard(flashcardQuestionInput.getText(), flashcardAnswerInput.getText());
            ApplicationManager.getInstance().getSelectedDeck().addFlashcard(flashcard);
            ApplicationManager.getInstance().setSelectedFlashcard(flashcard);
            Button button = new Button(flashcard.getQuestion());
            button.setPrefSize(362, 40);
            button.setFont(Font.font("Garamond", 18));
            button.setOnAction(event -> selectFlashcard(button));
            vBox.getChildren().add(button);
        }
    }

    @FXML
    private void editFlashcard(){
        if(ApplicationManager.getInstance().getSelectedFlashcard() != null) {
            findButtonByQuestion(ApplicationManager.getInstance().getSelectedFlashcard().getQuestion()).setText(flashcardQuestionInput.getText());
            ApplicationManager.getInstance().getSelectedFlashcard().setQuestion(flashcardQuestionInput.getText());
            ApplicationManager.getInstance().getSelectedFlashcard().setAnswer(flashcardAnswerInput.getText());
        }
    }

    @FXML
    private void deleteFlashcard(){
        if(ApplicationManager.getInstance().getSelectedFlashcard() != null) {
            ApplicationManager.getInstance().getSelectedDeck().deleteFlashcard(ApplicationManager.getInstance().getSelectedFlashcard());
            vBox.getChildren().removeIf(node -> node instanceof Button && ((Button) node).getText().equals(ApplicationManager.getInstance().getSelectedFlashcard().getQuestion()));
        }
    }

    private Button findButtonByQuestion(String question) {
        for (Node child : vBox.getChildren()) {
            if (child instanceof Button button) {
                if (button.getText().equals(question)) {
                    return button;
                }
            }
        }
        return null;
    }

    private void loadFlashcards(LinkedList<Flashcard> flashcards) {
        for (Flashcard flashcard : flashcards) {
            Button button = new Button(flashcard.getQuestion());
            button.setPrefSize(362, 40);
            button.setFont(Font.font("Garamond", 18));
            button.setOnAction(event -> selectFlashcard(button));
            vBox.getChildren().add(button);
        }
    }
}
