package boardgame.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;

import static org.junit.jupiter.api.Assertions.*;

public class HighscoreTest {

    Highscore highscore;

    Player player1;
    Player player2;
    Player player3;

    @BeforeEach
    public void init() {

        highscore = new Highscore();

        player1 = new Player("Adam");
        player2 = new Player("Bob");
        player3 = new Player("Charlie");

        highscore.addScore(player1);
        highscore.addScore(player2);
        highscore.addScore(player3);

    }

    @Test
    public void testGetScores() {

        assertEquals(highscore.getScores().get(0), player1);
        assertEquals(highscore.getScores().get(1), player2);
        assertEquals(highscore.getScores().get(2), player3);

    }

    @Test
    public void testGetScore() {

        assertEquals(highscore.getScore(), player1);

    }


    @Test
    public void testSetScores() {

        Player testPlayer = new Player("Daniel");
        List<Player> testScore = new ArrayList<>();

        testScore.add(testPlayer);
        highscore.setScores(testScore);

        assertEquals(highscore.getScore(), testPlayer);

    }

    @Test
    public void testOrder() {

        highscore.getScores().get(0).setMoves(120);
        highscore.getScores().get(1).setMoves(110);
        highscore.getScores().get(2).setMoves(100);

        highscore.order();

        assertEquals(highscore.getScores().get(0), player3);
        assertEquals(highscore.getScores().get(1), player2);
        assertEquals(highscore.getScores().get(2), player1);

    }

    @Test
    public void testFlush() {

        assertEquals(highscore.getScores().get(0), player1);
        assertEquals(highscore.getScores().get(1), player2);
        assertEquals(highscore.getScores().get(2), player3);

        this.highscore.flush();

        assertNull(highscore.getScores());

    }

    @Test
    public void testToAndFromXml() throws JAXBException {

        this.highscore.toXML();
        this.highscore.flush();
        this.highscore.fromXML();

        assertEquals(highscore.getScores().get(0).toString(), player1.toString());
        assertEquals(highscore.getScores().get(1).toString(), player2.toString());
        assertEquals(highscore.getScores().get(2).toString(), player3.toString());
    }

    @Test
    public void testSaveScores() throws JAXBException {

        Player testPlayer = new Player("Daniel");
        List<Player> testScore = new ArrayList<>();


        this.highscore.flush();
        this.highscore.toXML();
        this.highscore.saveScores();

        player1.setMoves(120);
        player2.setMoves(110);
        player3.setMoves(100);

        testScore.add(player3);
        testScore.add(player2);
        testScore.add(player1);
        this.highscore.setScores(testScore);
        this.highscore.saveScores();
        testScore = new ArrayList<>();

        testPlayer.setMoves(110);
        testScore.add(testPlayer);
        this.highscore.setScores(testScore);
        this.highscore.saveScores();

        this.highscore.fromXML();
        assertEquals(this.highscore.getScores().get(0).toString(), player3.toString());
        assertEquals(this.highscore.getScores().get(1).toString(), player2.toString());
        assertEquals(this.highscore.getScores().get(2).toString(), testPlayer.toString());
        assertEquals(this.highscore.getScores().get(3).toString(), player1.toString());

    }
}
