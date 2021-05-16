package boardgame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    Player player;


    @BeforeEach
    public void init() {
        player = new Player("Adam");
    }

    @Test
    public void testPlayer() {
        assertEquals(player.getName(), "Adam");
        assertEquals(player.getMoves(), 0);
    }

    @Test
    public void testSetName() {
        player.setName("Bob");
        assertEquals(player.getName(), "Bob");
    }

    @Test
    public void testSetMoves() {
        player.setMoves(1);
        assertEquals(player.getMoves(), 1);
    }

    @Test
    public void testIncreaseMoves() {
        assertEquals(player.getMoves(), 0);
        player.increaseMoves();
        assertEquals(player.getMoves(), 1);
        player.increaseMoves();
        assertEquals(player.getMoves(), 2);
    }

    @Test
    public void testCompareTo() {
        Player testPlayer1 = new Player("Bob");
        Player testPlayer2 = new Player("Charlie");
        Player testPlayer3 = new Player("Daniel");

        player.increaseMoves();
        testPlayer2.increaseMoves();
        testPlayer3.increaseMoves();
        testPlayer3.increaseMoves();

        assertEquals(player.compareTo(testPlayer1), 1);
        assertEquals(player.compareTo(testPlayer2), 0);
        assertEquals(player.compareTo(testPlayer3), -1);
    }
}
