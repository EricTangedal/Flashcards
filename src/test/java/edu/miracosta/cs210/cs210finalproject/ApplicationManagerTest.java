package edu.miracosta.cs210.cs210finalproject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationManagerTest {
    private Deck testDeck1;

    @BeforeEach
    void setUp() {
        //Create new deck
        testDeck1 = new Deck("Test Deck");
        //Create 3 flashcards
        Flashcard fc1 = new Flashcard("Q1", "A1");
        Flashcard fc2 = new Flashcard("Q2", "A2");
        Flashcard fc3 = new Flashcard("Q3", "A3");
        //Add flashcards to deck
        testDeck1.addFlashcard(fc1);
        testDeck1.addFlashcard(fc2);
        testDeck1.addFlashcard(fc3);
        //Add deck to list of decks
        ApplicationManager.getInstance().addDeck(testDeck1);
    }

    @AfterEach
    void tearDown() {
        ApplicationManager.getInstance().deleteDeck(testDeck1);
    }

    /*
    Author:
        Justin
    Test Case:
        Tests the save and load methods from the Application Manager class.
    Description:
        Saves a LinkedList of Deck objects to a file, then loads that file
        back in to verify the contents of the file match the expected result.
    */
    @Test
    void saveAndLoad() {
        //Save decks to file
        ApplicationManager.save(ApplicationManager.getInstance().getDecks());
        //Load decks from file
        assertEquals(ApplicationManager.getInstance().getDecks().toString(), ApplicationManager.load().toString());
    }

    /*
    Author:
        Ash
    Test Case:
        Tests the deleteDeck() method from the ApplicationManager class.
    Description:
        Checks the amount of decks in the list, then deletes the only deck, then check the amount of decks again.
     */
    @Test
    void deleteDeck() {
        assertEquals(1, ApplicationManager.getInstance().getDecks().size());
        ApplicationManager.getInstance().deleteDeck(testDeck1);
        assertEquals(0, ApplicationManager.getInstance().getDecks().size());
    }
}