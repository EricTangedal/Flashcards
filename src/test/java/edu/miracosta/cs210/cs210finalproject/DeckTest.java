package edu.miracosta.cs210.cs210finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Author: Eric
class DeckTest {
    Deck testDeck;
    Flashcard fc1, fc2, fc3;

    //Initialize testDeck with sample flashcards.
    @BeforeEach
    void setUp() {
        testDeck = new Deck("testDeck");
        fc1 = new Flashcard("Q1", "A1");
        fc2 = new Flashcard("Q2", "A2");
        fc3 = new Flashcard("Q3", "A3");
    }

    /*
    Test Case:
        Tests the addFlashcard() method of the Deck class.
    Description:
        Adds three flashcards to a deck and verifies that they have been stored correctly.
    */
    @Test
    void addFlashcard() {
        testDeck.addFlashcard(fc1);
        testDeck.addFlashcard(fc2);
        testDeck.addFlashcard(fc3);
        assertEquals("Q1",testDeck.getFlashcards().getFirst().getQuestion());
        assertEquals("Q2",testDeck.getFlashcards().get(1).getQuestion());
        assertEquals("Q3",testDeck.getFlashcards().getLast().getQuestion());
        assertEquals("A1",testDeck.getFlashcards().getFirst().getAnswer());
        assertEquals("A2",testDeck.getFlashcards().get(1).getAnswer());
        assertEquals("A3",testDeck.getFlashcards().getLast().getAnswer());
    }
}