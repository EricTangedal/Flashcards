package edu.miracosta.cs210.cs210finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Author: Dat
class FlashcardTest {
    Flashcard fc;

    //Initialize flashcard with sample question and answer.
    @BeforeEach
    void setUp() {
        fc = new Flashcard("Q1", "A1");
    }

    /*
    Test Case:
        Tests the setQuestion() method using the getQuestion() method of the Flashcard class.
    Description:
        Sets the question of a flashcard to a different question, then verifies the question of the flashcard.
    */
    @Test
    void setQuestion() {
        fc.setQuestion("Q2");
        assertEquals("Q2", fc.getQuestion());
    }

    /*
    Test Case:
        Tests the setAnswer() method using the getAnswer() method of the Flashcard class.
    Description:
        Sets the answer of a flashcard to a different answer, then verifies the answer of the flashcard.
    */
    @Test
    void setAnswer() {
        fc.setAnswer("A2");
        assertEquals("A2", fc.getAnswer());
    }
}