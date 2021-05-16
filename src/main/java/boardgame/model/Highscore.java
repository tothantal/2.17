package boardgame.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;

import org.tinylog.Logger;

/**
 * A class for storing and loading the highscores of players.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Highscore {

    private List<Player> scores = new ArrayList<>();

    public Highscore() {
        Logger.info("Creating new empty Highscore object");
        this.scores = new ArrayList<>();
    }

    public Highscore(List<Player> scores) {
        Logger.info("Creating new Highscore object with custom parameters");
        this.scores = scores;
    }

    public Highscore(Player score) {
        Logger.info("Creating new Highscore object with one Player inside");
        scores.add(score);
    }


    @Override
    public String toString() {
        Logger.info("Converting Highscore to String");
        StringBuilder result = new StringBuilder();
        for (Player i : this.getScores()) {
            result.append(i.getName()).append(" : ").append(i.getMoves()).append("\n");
        }
        return result.toString();
    }

    /**
     * Gets the list of {@code Player}s stored
     * @return	the list of {@code Player}s stored
     */
    public List<Player> getScores() {
        Logger.info("Getting the scores of players");
        return this.scores;
    }


    /**
     * Gets the first {@code Player} in the list
     * @return	the first {@code Player} in the list
     */
    public Player getScore() {
        Logger.info("Getting the first player in the list");
        return this.getScores().get(0);
    }


    /**
     * Sets the {@code Player}s in the list
     * @param scores	the scores of {@code Player}s
     */
    public void setScores(List<Player> scores) {
        Logger.info("Setting s");
        this.scores = scores;
    }


    /**
     * Adds one {@code Player} to the list
     * @param player	the {@code Player} to be added
     */
    public void addScore(Player player) {
        this.scores.add(player);
    }


    /**
     * Orders the {@code Player}s based on the number of moves they have made.
     * The less moves, the better their score.
     */
    public void order() {
        Collections.sort(this.scores);
    }

    /**
     * Empties the list of scores.
     */
    public void flush() {
        this.scores = null;
    }

    /**
     * Saves the scores to an XML file
     * @throws JAXBException    JAXBException
     */
    public void toXML() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Highscore.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(new Highscore(this.getScores()), new File("scores.xml"));
        this.flush();
    }

    /**
     * Loads the scores from an XML file
     * @throws JAXBException    JAXBException
     */
    public void fromXML() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Highscore.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Highscore unmarshalled = (Highscore) unmarshaller.unmarshal(new File("scores.xml"));

        this.setScores(unmarshalled.getScores());
    }

    /**
     * Saves the current {@code Highscore} without deleting all the other ones stored in the file.
     * Also orders all of the scores.
     * @throws JAXBException    JAXBException
     */
    public void saveScores() throws JAXBException {
        Highscore helper = new Highscore(this.getScores());
        this.flush();
        this.fromXML();

        if (helper.getScores() != null) {
            for (Player i : helper.getScores()) {
                this.addScore(i);
            }
        }

        this.order();
        this.toXML();
        this.flush();
    }
}
