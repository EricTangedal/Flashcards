package edu.miracosta.cs210.cs210finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.ResourceBundle;

public class QuizController implements Initializable {
    @FXML
    Label quizLabel, questionLabel, scoreLabel;
    @FXML
    TextField answerField;

    private String correctAnswer;
    private int score, count;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quizLabel.setText(ApplicationManager.getInstance().getSelectedDeck().getDeckName() + " Quiz");
        questionLabel.setText(ApplicationManager.getInstance().getSelectedDeck().getFlashcards().getFirst().getQuestion());
        ApplicationManager.getInstance().setSelectedFlashcard(ApplicationManager.getInstance().getSelectedDeck().getFlashcards().getFirst());
        correctAnswer = ApplicationManager.getInstance().getSelectedFlashcard().getAnswer();
        count = 0;
        score = 0;
        scoreLabel.setText("Score: " + score + "/" + ApplicationManager.getInstance().getSelectedDeck().getFlashcards().size());
    }

    private void RestartQuiz() {
        try {
            Parent quizFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Quiz.fxml")));
            Scene quizScene = new Scene(quizFXML);
            ApplicationManager.getInstance().getStage().setScene(quizScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Home() {
        try {
            Parent menuFXML = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
            Scene menuScene = new Scene(menuFXML);
            ApplicationManager.getInstance().getStage().setScene(menuScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void checkAnswer() {
        String answer = answerField.getText();
        if (answer.equalsIgnoreCase(correctAnswer)) {
            showAlert("Correct!");
            score++;
        } else {
            showAlert("Incorrect!");
        }
        count++;
        scoreLabel.setText("Score: " + score + "/" + ApplicationManager.getInstance().getSelectedDeck().getFlashcards().size());
        if(count < ApplicationManager.getInstance().getSelectedDeck().getFlashcards().size()){
            nextQuestion();
        }
        else {
            showAlert("Congratulations, you've completed the quiz and scored " + scorePercentage() + ".");
            RestartQuiz();
        }
    }

    private void nextQuestion() {
        ApplicationManager.getInstance().selectNextFlashcard();
        questionLabel.setText(ApplicationManager.getInstance().getSelectedFlashcard().getQuestion());
        correctAnswer = ApplicationManager.getInstance().getSelectedFlashcard().getAnswer();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quiz Result");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String scorePercentage(){
        DecimalFormat df = new DecimalFormat("##.#%");
        double percent = ((float)score / ApplicationManager.getInstance().getSelectedDeck().getFlashcards().size());
        return df.format(percent);
    }
}