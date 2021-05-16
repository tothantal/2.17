package boardgame.model;

import org.tinylog.Logger;

/**
 * Represents a given tile on the game board
 */
public class Tile {

    private int value;
    private Position position;
    private boolean marked;


    /**
     * Creates a new {@code Tile} with the specified and position and value
     *
     * @param value 	the number which is at this position
     * @param position 	the assigned position
     */
    public Tile(int value, Position position) {
        this.value = value;
        this.position = position;
        this.marked = false;
        Logger.info("New Tile created");
    }


    /**
     * Returns the value part of {@code Tile} as a {@code String}
     */
    @Override
    public String toString() {
        Logger.info("Converting Tile to String");
        return "" + value;
    }

    /**
     * Two {@code Tile} objects are equal if their values and their {@code Positions} are equal
     */
    @Override
    public boolean equals(Object obj) {
        Tile other = (Tile) obj;

        Logger.info("Comparing Tiles...");
        if(this.value == other.value && this.getPosition().equals(other.getPosition()))
            return true;

        return false;
    }


    /**
     * Gets {@code Position} of a {@code Tile} object
     *
     * @return 	the position of the tile
     */
    public Position getPosition() {
        Logger.info("Getting position of tile");
        return position;
    }


    /**
     *  Sets the {@code Position} of a {@code Tile} object to position
     *
     *  @param position the new position of the tile
     */
    public void setPosition(Position position) {
        Logger.info("Setting position of tile");
        this.position = position;
    }


    /**
     * Get the value of {@code Tile}
     *
     * @return the value on the tile
     */
    public int getValue() {
        Logger.info("Getting position of tile");
        return value;
    }


    /**
     * Set the value of the {@code Tile} object
     *
     * @param value	The value assigned to the given tile
     */
    public void setValue(int value) {
        Logger.info("Setting value of tile");
        this.value = value;
    }


    /**
     * Checks whether the {@code Tile} object is marked or not
     *
     * @return whether the Tile is marked or not
     */
    public boolean isMarked() {
        Logger.info("Checking whether the tile is marked");
        return this.marked;
    }


    /**
     * Marks a {code Tile} object if it isn't already marked
     */
    public void mark() {
        if(!this.isMarked()) {
            Logger.info("Setting mark on the tile");
            this.marked = true;
            Logger.info("Tile has been marked");
        } else { Logger.warn("Tile is already marked"); }
    }


    /**
     * Unmarks a Tile if it is marked
     */
    public void demark() {
        if(this.isMarked()) {
            Logger.info("Demarking tile");
            this.marked = false;
            Logger.info("Tile has been demarked");
        } else { Logger.warn("Tile is already not marked"); }
    }


    /**
     * Checks whether the {@code Tile} is empty or not
     * @return	whether the {@code Tile} is empty or not
     */
    public boolean isEmpty() {
        Logger.info("Checking whether Tile is empty");
        return this.value == 0;
    }

    /**
     * Gets whether two {@code Tile}s are  upper, lower, right or left neighbours
     *
     * @param other The other {@code Tile}
     * @return      The {code Direction} the other {@code Tile} is
     */
    public Direction getNeighbour(Tile other) {
        if (this.getPosition().getUpperNeighbour().equals(other.getPosition())) {
            return Direction.UP;
        } else if (this.getPosition().getLowerNeighbour().equals(other.getPosition())) {
            return Direction.DOWN;
        } else if (this.getPosition().getRightNeighbour().equals(other.getPosition())) {
            return Direction.RIGHT;
        } else if (this.getPosition().getLeftNeighbour().equals(other.getPosition())) {
            return Direction.LEFT;
        }

        return Direction.NONE;
    }
}
