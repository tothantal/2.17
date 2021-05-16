package boardgame.model;

import org.tinylog.Logger;

/**
 * Represents the player, with a name, and the number of moves s/he made as his or her score
 */
public class Player implements Comparable<Player> {

    private String name;
    private int moves;


    /**
     * The String form of {@code Player} is
     * "name : moves"
     */
    @Override
    public String toString() {
        Logger.info("Converting Player object to String");
        return "" + this.name + " : " + moves;
    }

    /**
     * Creates a {@code Player} with a name
     *
     * @param name	The name of the {@code Player}
     */
    public Player(String name) {
        Logger.info("Creating new Player object with name " + name);
        this.name = name;
        this.moves = 0;
    }

    public Player() {

    }


    /**
     *  Increases the number of moves by 1
     */
    public void increaseMoves() {
        Logger.info("Increasing number of moves");
        this.moves = this.moves + 1;
    }


    /**
     * Returns the name of the {@code Player}
     *
     * @return 	the name of the {@code Player} represented by a string
     */
    public String getName() {
        Logger.info("Getting name of player");
        return name;
    }


    /**
     * Sets the name of the {@code Player}
     *
     * @param name	the name to be given to the {@code Player}
     */
    public void setName(String name) {
        Logger.info("Setting name of player");
        this.name = name;
    }


    /**
     * Returns the number of moves made so far
     *
     * @return	the number of moves made so far
     */
    public int getMoves() {
        Logger.info("Getting the number of moves from player");
        return moves;
    }


    /**
     * Set the number of moves made so far by the {@code Player} to moves
     *
     * @param moves	the new number of moves
     */
    public void setMoves(int moves) {
        Logger.info("Setting the number of moves of player");
        this.moves = moves;
    }


    /**
     * Compares the number of moves made by the players
     */
    public int compareTo(Player other) {
        Logger.info("Comparing two players...");
        return Double.compare(this.moves, other.moves);
    }
}
