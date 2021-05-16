package boardgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import boardgame.model.Direction;
import boardgame.model.Game;
import boardgame.model.Position;



public class GameControllerTest {


    Game game;
    GameController controller;

    @BeforeEach
    public void init() {
        game = new Game("default");

        controller = new GameController(game);
    }

    @Test
    public void testSetPlayerName() {
        assertEquals(controller.getPlayerName(), "default");
        controller.setPlayerName("Bela");
        assertEquals(controller.getPlayerName(), "Bela");
    }

    @Test
    public void testSetPlayerMoves() {
        assertEquals(controller.getPlayerMoves(), 0);
        controller.setPlayerMoves(1);
        assertEquals(controller.getPlayerMoves(), 1);
    }

    @Test
    public void testSetCurrentPosition() {
        Position testPosition = new Position(1, 6);

        assertEquals(controller.getCurrentPosition(), new Position(1, 0));
        controller.setCurrentPosition(testPosition);
        assertEquals(controller.getCurrentPosition(), testPosition);
    }

    @Test
    public void testGetPlayerName() {
        assertEquals(controller.getPlayerName(), "default");
    }

    @Test
    public void testGetPlayerMoves() {
        assertEquals(controller.getPlayerMoves(), 0);
    }

    @Test
    public void testGetCurrentPosition() {
        assertEquals(controller.getCurrentPosition(), new Position(1, 0));
    }

    @Test
    public void testIncreasePlayerMoves() {
        assertEquals(controller.getPlayerMoves(), 0);
        controller.increasePlayerMoves();
        assertEquals(controller.getPlayerMoves(), 1);
        controller.increasePlayerMoves();
        controller.increasePlayerMoves();
        assertEquals(controller.getPlayerMoves(), 3);
    }

    @Test
    public void testMoveCurrentPositionValidMoves() {
        Position testPosition = new Position(1, 0);

        assertEquals(controller.getCurrentPosition(), testPosition);

        controller.moveCurrentPosition(Direction.RIGHT);
        testPosition = new Position(1, 1);
        assertEquals(controller.getCurrentPosition(), testPosition);

        controller.moveCurrentPosition(Direction.RIGHT);
        controller.moveCurrentPosition(Direction.RIGHT);
        controller.moveCurrentPosition(Direction.UP);
        testPosition = new Position(0, 3);
        assertEquals(controller.getCurrentPosition(), testPosition);

        controller.moveCurrentPosition(Direction.DOWN);
        testPosition = new Position(1, 3);
        assertEquals(controller.getCurrentPosition(), testPosition);

        controller.moveCurrentPosition(Direction.LEFT);
        testPosition = new Position(1, 2);
        assertEquals(controller.getCurrentPosition(), testPosition);
    }

    @Test
    public void testMoveCurrentPositionInvalidMoves() {
        Position testPosition = new Position(1, 0);

        assertEquals(controller.getCurrentPosition(), testPosition);
        controller.moveCurrentPosition(Direction.UP);
        controller.moveCurrentPosition(Direction.LEFT);
        controller.moveCurrentPosition(Direction.DOWN);
        assertEquals(controller.getCurrentPosition(), testPosition);

        testPosition = new Position(1, 9);
        controller.setCurrentPosition(testPosition);
        assertEquals(controller.getCurrentPosition(), testPosition);
        controller.moveCurrentPosition(Direction.RIGHT);
        assertEquals(controller.getCurrentPosition(), testPosition);

        testPosition = new Position(0, 3);
        controller.setCurrentPosition(testPosition);
        assertEquals(controller.getCurrentPosition(), testPosition);
        controller.moveCurrentPosition(Direction.UP);
        controller.moveCurrentPosition(Direction.RIGHT);
        controller.moveCurrentPosition(Direction.LEFT);
        assertEquals(controller.getCurrentPosition(), testPosition);


    }

    @Test
    public void testMoveTowards() {
        assertEquals(game.getBoard().toString(), "[( 0 0 0 0 0 0 0 0 0 0 )( 0 2 3 4 5 6 7 8 9 1 )]");

        controller.moveCurrentPosition(Direction.RIGHT);
        controller.moveTowards(Direction.LEFT);
        assertEquals(game.getBoard().toString(), "[( 0 0 0 0 0 0 0 0 0 0 )( 2 0 3 4 5 6 7 8 9 1 )]");

        controller.moveCurrentPosition(Direction.RIGHT);
        controller.moveTowards(Direction.LEFT);
        assertEquals(game.getBoard().toString(), "[( 0 0 0 0 0 0 0 0 0 0 )( 2 3 0 4 5 6 7 8 9 1 )]");

        controller.moveCurrentPosition(Direction.RIGHT);
        controller.moveTowards(Direction.UP);
        assertEquals(game.getBoard().toString(), "[( 0 0 0 4 0 0 0 0 0 0 )( 2 3 0 0 5 6 7 8 9 1 )]");

        controller.moveCurrentPosition(Direction.UP);
        controller.moveTowards(Direction.DOWN);
        assertEquals(game.getBoard().toString(), "[( 0 0 0 0 0 0 0 0 0 0 )( 2 3 0 4 5 6 7 8 9 1 )]");

        controller.moveCurrentPosition(Direction.DOWN);
        controller.moveTowards(Direction.LEFT);
        assertEquals(game.getBoard().toString(), "[( 0 0 0 0 0 0 0 0 0 0 )( 2 3 4 0 5 6 7 8 9 1 )]");

        assertEquals(controller.getPlayerMoves(), 5);
    }
}
