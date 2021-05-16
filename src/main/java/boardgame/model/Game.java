package boardgame.model;

import javax.xml.bind.JAXBException;

/**
 * The Model part of the MVC architecture
 * The Game Data is represented by this object
 */
public class Game {

    private Player player;
    private Board board;
    private Position position;
    private Highscore score;

    public Game(String Name) {
        this.player = new Player(Name);
        this.board = new Board();
        this.position = new Position(1, 0);
        this.score = new Highscore(this.player);
    }


    /**
     * Gets the current score and name of the player
     * @return	the score and name of the current player
     */
    public Highscore getScore() {
        return score;
    }


    /**
     * Sets the current score and name of the player
     * @param	the	current score and name of the player
     */
    public void setScore(Highscore score) {
        this.score = score;
    }


    /**
     * Saves the score of the current player
     * @throws JAXBException
     */
    public void saveScore() throws JAXBException {
        this.score.saveScores();
    }


    /**
     * Gets the {@code Player}
     *
     * @return	The {@code Player}
     */
    public Player getPlayer() {
        return this.player;
    }


    /**
     * Sets the {@code Player}
     * @param 	the new {@code Player}
     */
    public void setPlayer(Player player) {
        this.player = player;
    }


    /**
     * Gets the game board
     *
     * @return	the {@code Board}
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Sets the game board
     *
     * @param 	the new {@code Board}
     */
    public void setBoard(Board board) {
        this.board = board;
    }


    /**
     * Gets the current {@code Position}
     *
     * @return	the current {@code Position}
     */
    public Position getPosition() {
        return this.position;
    }


    /**
     * Sets the current {@code Position}
     *
     * @param 	the new {@code Position}
     */
    public void setPosition(Position position) {
        this.position = position;
    }


    /**
     * Moves the current {@code Position} towards the specified {@code Direction} by one.
     *
     * @param direction	the {@code Direction} the {@code Position} will change towards by one
     */
    public void move(Direction direction) {
        switch(direction) {
            case UP:
                if(this.getPosition().canMoveUp()) {
                    this.setPosition(this.getPosition().getUpperNeighbour());
                }
                break;
            case DOWN:
                if(this.getPosition().canMoveDown()) {
                    this.setPosition(this.getPosition().getLowerNeighbour());
                }
                break;
            case LEFT:
                if(this.getPosition().canMoveLeft()) {
                    this.setPosition(this.getPosition().getLeftNeighbour());
                }
                break;
            case RIGHT:
                if(this.getPosition().canMoveRight()) {
                    this.setPosition(this.getPosition().getRightNeighbour());
                }
                break;
        }
    }

    /**
     * Moves the contents of the {@code Tile} at the current {@code Position}
     * towards the specified {@code Direction}. Will only do this if the move is valid.
     *
     * @param direction	the specified {@code Direction} of movement
     */
    public boolean moveTowards(Direction direction) {
        this.getBoard().move(this.getPosition(), direction);
        this.getPlayer().increaseMoves();
        return this.getBoard().isOrdered();
    }
    
    
    
    public void moveTiles(Tile one, Tile other) {

    	Direction direction = Direction.NONE;
    	
    	if (other.getPosition().getRow() > one.getPosition().getRow()) {
    		direction = Direction.UP;
    	}
    	
    	if (other.getPosition().getRow() < one.getPosition().getRow()) {
    		direction = Direction.DOWN;
    	}
    	
    	if (other.getPosition().getColumn() > one.getPosition().getColumn()) {
    		direction = Direction.RIGHT;
    	}
    	
    	if (other.getPosition().getRow() > one.getPosition().getRow()) {
    		direction = Direction.LEFT;
    	}
    	
        this.getBoard().move(one.getPosition(), direction);
    }
}
