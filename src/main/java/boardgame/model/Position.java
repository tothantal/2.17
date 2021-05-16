package boardgame.model;

import org.tinylog.Logger;

/**
 * Represents a position (row, column).
 */
public class Position {

    private int row;
    private int column;

    /**
     * Creates a new {@code Position} with (Y, X) coordinates.
     *
     * @param row		The Y coordinate, number of rows from 0
     * @param column	The X coordinate, number of columns from 0
     */
    public Position(int row, int column) {
        Logger.info("Creating new Position");
        this.row = row;
        this.column = column;
    }


    /**
     * Gets the Y coordinate (row) of the {@code Position}.
     *
     * @return	The Y coordinate
     */
    public int getRow() {
        Logger.info("Getting row number of Position");
        return row;
    }


    /**
     * Sets the Y coordinate (row) of the {@code Position}.
     *
     * @param row	the Y coordinate
     */
    public void setRow(int row) {
        Logger.info("Setting row number of Position");
        this.row = row;
    }


    /**
     * Gets the X coordinate (column) of the {@code Position}.
     *
     * @return	The X coordinate
     */
    public int getColumn() {
        Logger.info("Getting column number of Position");
        return column;
    }


    /**
     * Sets the X coordinate (column) of {@code Position}.
     *
     * @param column	The X coordinate
     */
    public void setColumn(int column) {
        Logger.info("Setting column number of Position");
        this.column = column;
    }


    /**
     * Converts the {@code Position} to a {@code String}.
     * The String form is "(row column)".
     */
    @Override
    public String toString() {
        Logger.info("Converting Position to String");
        return "(" + this.row + " " + this.column + ")";
    }


    /**
     * Compares two Points.
     * Two Points are equal if their coordinates are equal.
     */
    @Override
    public boolean equals(Object obj) {

        Position other = (Position) obj;

        Logger.info("Comparing Positions...");
        return this.column == other.column && this.row == other.row;
    }


    /**
     * Gets the neighbour {@code Position} one above the current one.
     *
     * @return The {@code Position} one above the current one.
     */
    public Position getUpperNeighbour() {
        Logger.info("Getting upper neighbour of Position");
        return new Position(this.row - 1, this.column);
    }


    /**
     * Gets the neighbour {@code Position} one below the current one.
     *
     * @return The {@code Position} one below the current one
     */
    public Position getLowerNeighbour() {
        Logger.info("Getting lower neighbour of Position");
        return new Position(this.row + 1, this.column);
    }


    /**
     * Gets the neighbour {@code Position} one to the left of the current one.
     *
     * @return The {@code Position} one to the left of the current one
     */
    public Position getLeftNeighbour() {
        Logger.info("Getting neighbour to the left of Position");
        return new Position(this.row, this.column - 1);
    }


    /**
     * Gets the neighbour {@code Position} one to the right of the current one.
     *
     * @return The {@code Position} one to the right of the current one
     */
    public Position getRightNeighbour() {
        Logger.info("Getting neighbour to the right of Position");
        return new Position(this.row, this.column + 1);
    }


    /**
     * Checks whether it is a valid move to go to the {@code Position} one above the current one.
     *
     * @return  whether it is a valid move to go to the {@code Position} one above the current one
     */
    public boolean canMoveUp() {
        Position neighbour = this.getUpperNeighbour();

        Logger.info("Checking if upper neighbour is available for moving");
        if (neighbour.getRow() != 0) {
            return false;
        }
        return this.getColumn() == 3 ||
                this.getColumn() == 5 ||
                this.getColumn() == 7;
    }


    /**
     * Checks whether it is a valid move to go to the {@code Position} one below the current one.
     *
     * @return  whether it is a valid move to go to the {@code Position} one below the current one
     */
    public boolean canMoveDown() {

        Logger.info("Checking if lower neighbour is available for moving");
        if (this.getRow() != 0) {
            return false;
        }
        return this.getColumn() == 3 ||
                this.getColumn() == 5 ||
                this.getColumn() == 7;
    }


    /**
     * Checks whether it is a valid move to go to the {@code Position} one to the left of the current one.
     *
     * @return  whether it is a valid move to go to the {@code Position} one to the left of the current one
     */
    public boolean canMoveLeft() {

        Logger.info("Checking if neighbour to the left is available for moving");
        if (this.getRow() != 1) {
            return false;
        }
        return this.getColumn() > 0 && this.getColumn() < 10;
    }


    /**
     * Checks whether it is a valid move to go to the {@code Position} one to the right of the current one.
     *
     * @return  whether it is a valid move to go to the {@code Position} one to the right of the current one
     */
    public boolean canMoveRight() {

        Logger.info("Checking if neighbour to the right is available for moving");
        if (this.getRow() != 1) {
            return false;
        }
        return this.getColumn() > -1 && this.getColumn() < 9;
    }
}
