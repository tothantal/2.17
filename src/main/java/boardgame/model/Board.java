package boardgame.model;

import java.util.ArrayList;
import java.util.List;

import org.tinylog.Logger;

public class Board {

    /**
     * The board is represented as two rows of numbers
     */
    private List<Tile> firstRow;
    private List<Tile> secondRow;


    /**
     * Constructor for custom {@code Board} objects
     *
     * @param firstRow	The first row of {@code Tile} objects
     * @param secondRow	The second row of {@code Tile} objects
     */
    public Board(List<Tile> firstRow, List<Tile> secondRow) {

        Logger.info("Creating a new custom board");
        this.firstRow = firstRow;
        this.secondRow = secondRow;
    }

    /**
     * Creates the default {@code Board} object, representing the game board
     */
    public Board() {

        Logger.info("Creating a new board");

        Tile tiles;
        firstRow = new ArrayList<>();
        secondRow = new ArrayList<>();

        try {
            Logger.info("Creating the first row");
            for(int i = 0; i < 10; i++) {
                tiles = new Tile(0, new Position(0, i));
                this.firstRow.add(tiles);
            }
            Logger.info("First row created");

            Logger.info("Creating the second row");
            this.secondRow.add(new Tile(0, new Position(1, 0)));
            for(int i = 2; i < 10; i++) {
                tiles = new Tile(i, new Position(1, i - 1));
                this.secondRow.add(tiles);
            }
            this.secondRow.add(new Tile(1, new Position(1, 9)));
            Logger.info("Second row created");
        }

        catch (Exception e) {
            Logger.error(e);
        }
    }

    /**
     * Returns the String representation of the {@code Board} object
     * String form is: [( num1 num2 num3 ...etc )( num1 num2 num3 ...etc )]
     *
     * @return	The String representation
     */
    @Override
    public String toString() {

        Logger.info("Converting Board object to String");

        StringBuilder firstRowString = new StringBuilder("( ");
        for(int i = 0; i < 10; i++) {
            firstRowString.append(firstRow.get(i).toString()).append(" ");
        }
        firstRowString.append(")");

        StringBuilder secondRowString = new StringBuilder("( ");
        for(int i = 0; i < 10; i++) {
            secondRowString.append(secondRow.get(i).toString()).append(" ");
        }
        secondRowString.append(")");

        return "[" + firstRowString + secondRowString + "]";
    }


    /**
     * Gets the first row of the game board
     * @return	the first row of the game board
     */
    public List<Tile> getFirstRow() {
        Logger.info("Getting the first row of the board");
        return firstRow;
    }


    /**
     * Sets the first row of the game board
     * @param firstRow	the first row of the game board
     */
    public void setFirstRow(List<Tile> firstRow) {
        Logger.info("Setting the first row of the board");
        this.firstRow = firstRow;
    }

    /**
     * Gets the second row of the game board
     * @return	the second row of the game board
     */
    public List<Tile> getSecondRow() {
        Logger.info("Getting the second row of the board");
        return secondRow;
    }


    /**
     * Sets the second row of the game board
     * @param secondRow	the second row of the game board
     */
    public void setSecondRow(List<Tile> secondRow) {
        Logger.info("Setting the second row of the board");
        this.secondRow = secondRow;
    }


    /**
     * Checks if the game board is ordered correctly or not
     *
     * @return	whether the game board is ordered correctly or not
     */
    public boolean isOrdered() {

        String orderedBoardString = "[( 0 0 0 0 0 0 0 0 0 0 )( 1 2 3 4 5 6 7 8 9 0 )]";

        Logger.info("Checking order of Board");

        return this.toString().equals(orderedBoardString);
    }

    /**
     * Returns the {@code Tile} at position (X, Y) from {@code Board}
     *
     * @param position	The position in question
     * @return			The Tile at the given position
     */
    public Tile at(Position position) {

        Tile result = null;

        Logger.info("Getting tile at " + position.toString());

        try {

            if( (position.getColumn() < 0 || position.getColumn() > 9) ||
                    (position.getRow() < 0 || position.getRow() > 1 )) {
                Logger.error("Wrong index: " + position.getColumn() + position.getRow());
            } else {

                if(position.getRow() == 0) {
                    return this.firstRow.get(position.getColumn());
                }

                return this.secondRow.get(position.getColumn());
            }
        }

        catch (Exception e) {
            Logger.error(e);
        }

        return result;
    }

    /**
     * Swaps the {@code Position} of two {@code Tile}s
     *
     * @param one   The {@code Tile} to be swapped
     * @param other The {@code Tile} to be swapped with
     */
    public void move(Tile one, Tile other) {

        	Logger.info("Swapping tiles...");
        	
            switch(one.getNeighbour(other)) {
                case UP:
                    this.move(one.getPosition(), Direction.UP);
                    break;
                case DOWN:
                    this.move(one.getPosition(), Direction.DOWN);
                    break;
                case RIGHT:
                    this.move(one.getPosition(), Direction.RIGHT);
                    break;
                case LEFT:
                    this.move(one.getPosition(), Direction.LEFT);
                    break;
                case NONE:
                	break;
                default:
                    break;
            }
    }

    /**
     * Moves the Tile at position to the given direction
     *
     * @param position	the position of the tile
     * @param direction	the given direction
     */
    public void move(Position position, Direction direction) {

        Logger.info("Moving tile at " + position.toString());

        try {

                switch(direction) {
                    case UP:
                        if (position.canMoveUp()) {
                            if(!this.at(position.getUpperNeighbour()).toString().equals("0")) {
                                Logger.error("Cannot move tile: movement blocked");
                                break;
                            }
                            this.at(position.getUpperNeighbour()).setValue(this.at(position).getValue());
                            this.at(position).setValue(0);
                        }
                        break;

                    case DOWN:
                        if (position.canMoveDown()) {
                            if(!this.at(position.getLowerNeighbour()).toString().equals("0")) {
                                Logger.error("Cannot move tile: movement blocked");
                                break;
                            }
                            this.at(position.getLowerNeighbour()).setValue(this.at(position).getValue());
                            this.at(position).setValue(0);
                        }
                        break;

                    case RIGHT:
                        if (position.canMoveRight()) {
                            if(!this.at(position.getRightNeighbour()).toString().equals("0")) {
                                Logger.error("Cannot move tile: movement blocked");
                                break;
                            }
                            this.at(position.getRightNeighbour()).setValue(this.at(position).getValue());
                            this.at(position).setValue(0);
                        }
                        break;

                    case LEFT:
                        if (position.canMoveLeft()) {
                            if(!this.at(position.getLeftNeighbour()).toString().equals("0")) {
                                Logger.error("Cannot move tile: movement blocked");
                                break;
                            }
                            this.at(position.getLeftNeighbour()).setValue(this.at(position).getValue());
                            this.at(position).setValue(0);
                        }
                    	break;
                    	
                    case NONE:
                    	break;
                }
        }

        catch (Exception e) {
            Logger.error(e);
        }
    }
}