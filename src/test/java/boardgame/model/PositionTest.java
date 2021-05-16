package boardgame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PositionTest {

    Position position;

    @BeforeEach
    public void init() {
        position = new Position(0, 0);
    }

    @Test
    public void testPosition() {
        assertEquals(position.getRow(), 0);
        assertEquals(position.getColumn(), 0);
    }

    @Test
    public void testSetRow() {
        position.setRow(1);
        assertEquals(position.getRow(), 1);
    }

    @Test
    public void testSetColumn() {
        position.setColumn(1);
        assertEquals(position.getColumn(), 1);
    }

    @Test
    public void testToString() {
        assertEquals(position.toString(), "(0 0)");
    }

    @Test
    public void testEquals() {
        Position other = new Position(0, 0);
        assertEquals(position, other);
    }

    @Test
    public void testGetUpperNeighbour() {
        assertEquals(position.getUpperNeighbour().getRow(), -1);
        assertEquals(position.getUpperNeighbour().getColumn(), 0);
    }

    @Test
    public void testGetLowerNeighbour() {
        assertEquals(position.getLowerNeighbour().getRow(), 1);
        assertEquals(position.getLowerNeighbour().getColumn(), 0);
    }

    @Test
    public void testGetRightNeighbour() {
        assertEquals(position.getRightNeighbour().getRow(), 0);
        assertEquals(position.getRightNeighbour().getColumn(), 1);
    }

    @Test
    public void testGetLeftNeighbour() {

        assertEquals(position.getLeftNeighbour().getRow(), 0);
        assertEquals(position.getLeftNeighbour().getColumn(), -1);
    }

    @Test
    public void testCanMoveUp() {

        for (int i = -1; i < 11; i++) {
            position = new Position(-1, i);
            assertFalse(position.canMoveUp());
        }

        for (int i = -1; i < 11; i++) {
            position = new Position(0, i);
            assertFalse(position.canMoveUp());
        }

        for (int i = -1; i < 11; i ++) {
            position = new Position(1, i);

            if(i == 3 || i == 5 || i == 7) {
                assertTrue(position.canMoveUp());
            } else {
                assertFalse(position.canMoveUp());
            }
        }

        for (int i = -1; i < 11; i++) {
            position = new Position(2, i);
            assertFalse(position.canMoveUp());
        }
    }

    @Test
    public void testCanMoveDown() {

        for (int i = -1; i < 11; i++) {
            position = new Position(-1, i);
            assertFalse(position.canMoveDown());
        }

        for (int i = -1; i < 11; i++) {
            position = new Position(0, i);
            if(i == 3 || i == 5 || i == 7) {
                assertTrue(position.canMoveDown());
            } else {
                assertFalse(position.canMoveDown());
            }
        }

        for (int i = -1; i < 11; i ++) {
            position = new Position(1, i);
            assertFalse(position.canMoveDown());
        }

        for (int i = -1; i < 11; i++) {
            position = new Position(2, i);
            assertFalse(position.canMoveDown());
        }


    }

    @Test
    public void testCanMoveLeft() {
        for (int i = -1; i < 11; i++) {

            position = new Position(-1, i);
            assertFalse(position.canMoveLeft());

            position = new Position(0, i);
            assertFalse(position.canMoveLeft());

            position = new Position(1, i);
            if (i > 0 && i < 10) {
                assertTrue(position.canMoveLeft());
            } else {
                assertFalse(position.canMoveLeft());
            }

            position = new Position(2, i);
            assertFalse(position.canMoveLeft());
        }
    }

    @Test
    public void testCanMoveRight() {
        for (int i = -1; i < 10; i++) {

            position = new Position(-1, i);
            assertFalse(position.canMoveRight());

            position = new Position(0, i);
            assertFalse(position.canMoveRight());

            position = new Position(1, i);
            if (i > -1 && i < 9) {
                assertTrue(position.canMoveRight());
            } else {
                assertFalse(position.canMoveRight());
            }

            position = new Position(2, i);
            assertFalse(position.canMoveRight());
        }
    }
}
