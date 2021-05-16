package boardgame.model;


import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {

    Board board;

    @BeforeEach
    public void init() {
        board = new Board();
    }

    @Test
    public void testBoard() {

        String boardState = "[( 0 0 0 0 0 0 0 0 0 0 )( 0 2 3 4 5 6 7 8 9 1 )]";

        assertEquals(boardState, board.toString());
    }

    @Test
    public void testBoardWithCustomParameters() {

        List<Tile> testFirstRow = new ArrayList<>();
        List<Tile> testSecondRow = new ArrayList<>();
        String testBoardState = "[( 0 1 2 3 4 5 6 7 8 9 )( 0 1 2 3 4 5 6 7 8 9 )]";

        for(int i = 0; i < 10 ; i++) {
            testFirstRow.add(new Tile(i, new Position(0, i)));
            testSecondRow.add(new Tile(i, new Position(1, i)));
        }

        Board testBoard = new Board(testFirstRow, testSecondRow);

        assertEquals(testBoardState, testBoard.toString());
    }

    @Test
    public void testIsOrdered() {

        List<Tile> orderedFirstRow = new ArrayList<>();
        List<Tile> orderedSecondRow = new ArrayList<>();


        for(int i = 0; i < 9; i++) {
            orderedFirstRow.add(new Tile(0, new Position(0, i)));
            orderedSecondRow.add(new Tile(i + 1, new Position(1, i)));
        }

        orderedFirstRow.add(new Tile(0, new Position(0, 9)));
        orderedSecondRow.add(new Tile(0, new Position(0, 9)));

        Board orderedBoard = new Board(orderedFirstRow, orderedSecondRow);

        assertTrue(orderedBoard.isOrdered());
        assertFalse(board.isOrdered());
    }

    @Test
    public void testAt() {
        assertEquals("0", board.at(new Position(0, 0)).toString());
        assertEquals("0", board.at(new Position(1, 0)).toString());
        assertEquals("2", board.at(new Position(1, 1)).toString());
        assertEquals("3", board.at(new Position(1, 2)).toString());
    }

    @Test
    public void testMoveValidMoves() {

        Logger.info("Testing valid movements");
        board.move(new Position(1, 1), Direction.LEFT);
        assertEquals(board.toString(), "[( 0 0 0 0 0 0 0 0 0 0 )( 2 0 3 4 5 6 7 8 9 1 )]");
        board.move(new Position(1, 2), Direction.LEFT);
        assertEquals(board.toString(), "[( 0 0 0 0 0 0 0 0 0 0 )( 2 3 0 4 5 6 7 8 9 1 )]");
        board.move(new Position(1, 3), Direction.UP);
        assertEquals(board.toString(), "[( 0 0 0 4 0 0 0 0 0 0 )( 2 3 0 0 5 6 7 8 9 1 )]");
        board.move(new Position(0, 3), Direction.DOWN);
        assertEquals(board.toString(), "[( 0 0 0 0 0 0 0 0 0 0 )( 2 3 0 4 5 6 7 8 9 1 )]");
        board.move(new Position(1, 7), Direction.UP);
        assertEquals(board.toString(), "[( 0 0 0 0 0 0 0 8 0 0 )( 2 3 0 4 5 6 7 0 9 1 )]");
        board.move(new Position(1, 6), Direction.RIGHT);
        assertEquals(board.toString(), "[( 0 0 0 0 0 0 0 8 0 0 )( 2 3 0 4 5 6 0 7 9 1 )]");
        board.move(new Position(1, 3), Direction.LEFT);
        assertEquals(board.toString(), "[( 0 0 0 0 0 0 0 8 0 0 )( 2 3 4 0 5 6 0 7 9 1 )]");
    }

    @Test
    public void testMoveNothing() {
        Logger.info("Testing invalid movements");
        board.move(new Position(0, 0), Direction.LEFT);
        board.move(new Position(0, 0), Direction.RIGHT);
        board.move(new Position(0, 0), Direction.UP);
        board.move(new Position(0, 0), Direction.DOWN);

        board.move(new Position(1, 0), Direction.LEFT);
        board.move(new Position(1, 0), Direction.UP);
        board.move(new Position(1, 0), Direction.DOWN);
        board.move(new Position(1, 9), Direction.RIGHT);

        assertEquals(board.toString(), "[( 0 0 0 0 0 0 0 0 0 0 )( 0 2 3 4 5 6 7 8 9 1 )]");
    }

    @Test
    public void testMoveBlocked() {
        Logger.info("Testing invalid movements");
        Logger.info("Another tile in the way");
        board.move(new Position(1, 9), Direction.LEFT);	// Blocked
        board.move(new Position(1, 8), Direction.RIGHT); // Blocked
        board.move(new Position(1, 3), Direction.UP);
        board.move(new Position(1, 2), Direction.RIGHT);
        board.move(new Position(0, 3), Direction.DOWN); // Blocked
        board.move(new Position(1, 3), Direction.UP); // Blocked

        assertEquals(board.toString(), "[( 0 0 0 4 0 0 0 0 0 0 )( 0 2 0 3 5 6 7 8 9 1 )]");
    }

    @Test
    public void testMoveZeroValue() {
        Logger.info("Testing invalid movements");
        Logger.info("Attempting to move nothing");
        board.move(new Position(1, 0), Direction.RIGHT);
        assertEquals(board.toString(), "[( 0 0 0 0 0 0 0 0 0 0 )( 0 2 3 4 5 6 7 8 9 1 )]");
    }
}
