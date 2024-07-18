package edu.miracosta.cs210.cs210finalproject;

import java.io.Serializable;

public class Flashcard implements Serializable {
    private String question;
    private String answer;

    Flashcard(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String toString() {
        return question + " " + answer;
    }
}
