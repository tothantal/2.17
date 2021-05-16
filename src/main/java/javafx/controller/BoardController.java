package javafx.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import javafx.App;
import org.tinylog.Logger;

import boardgame.GameController;
import boardgame.model.Game;
import boardgame.model.Position;
import boardgame.model.Tile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class BoardController {


	private String playerName;
	private Game game;
	private GameController controller;


	Tile markedTile1;
	Tile markedTile2;

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@FXML
	public void initialize() {
		this.markedTile1 = null;
		this.markedTile2 = null;

		this.game = new Game(this.playerName);
		this.controller = new GameController(this.game);

		updateBoard();
	}

	public void updateBoard() {
		setTile(tile03, new Position(0, 3));
		setTile(tile05, new Position(0, 5));
		setTile(tile07, new Position(0, 7));

		setTile(tile10, new Position(1, 0));
		setTile(tile11, new Position(1, 1));
		setTile(tile12, new Position(1, 2));
		setTile(tile13, new Position(1, 3));
		setTile(tile14, new Position(1, 4));
		setTile(tile15, new Position(1, 5));
		setTile(tile16, new Position(1, 6));
		setTile(tile17, new Position(1, 7));
		setTile(tile18, new Position(1, 8));
		setTile(tile19, new Position(1, 9));

		Logger.info("Number of moves: " + controller.getPlayerMoves());
	}



	private void setTile(Button tile, Position position) {
		tile.setText(controller.getGameBoard().at(position).toString());
	}

	private void logic() throws IOException {

		if (controller.getPlayerName() == null) {
			controller.setPlayerName(this.playerName);
		}

		for (Tile tile : controller.getGameBoard().getFirstRow()) {
			if (tile.isMarked()) {
				if (this.markedTile1 != null && markedTile2 == null) {
					this.markedTile2 = tile;
				} else {
					this.markedTile1 = tile;
				}
			}
		}

		for (Tile tile : controller.getGameBoard().getSecondRow()) {
			if (tile.isMarked()) {
				if (this.markedTile1 != null && markedTile2 == null) {
					this.markedTile2 = tile;
				} else {
					this.markedTile1 = tile;
				}
			}
		}

		if (this.markedTile1 != null && this.markedTile2 != null) {


			controller.moveTiles(markedTile1, markedTile2);

			controller.getGameBoard().at(markedTile1.getPosition()).demark();
			controller.getGameBoard().at(markedTile2.getPosition()).demark();

			this.markedTile1 = null;
			this.markedTile2 = null;

		}

		if (controller.getGameBoard().isOrdered()) {
			try {

				File XMLFile = new File("score.xml");
				if (!XMLFile.exists()) {
					controller.getGame().getScore().toXML();
				} else {
					controller.getGame().getScore().saveScores();
				}

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/score.fxml"));
				Parent root = loader.load();

				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.setTitle("Highscore");
				stage.show();

			} catch (JAXBException e) {
				Logger.error(e);
			}
		}

	}



	@FXML
	private Button tile03;
	@FXML
	private Button tile05;
	@FXML
	private Button tile07;

	@FXML
	private Button tile10;
	@FXML
	private Button tile11;
	@FXML
	private Button tile12;
	@FXML
	private Button tile13;
	@FXML
	private Button tile14;
	@FXML
	private Button tile15;
	@FXML
	private Button tile16;
	@FXML
	private Button tile17;
	@FXML
	private Button tile18;
	@FXML
	private Button tile19;

	@FXML
	private ToggleButton tb;


	@FXML
	private void showScore() throws IOException {
		App.setRoot("secondary");
	}

	@FXML
	private void markTile03() throws IOException {
		controller.getGameBoard().getFirstRow().get(3).mark();
		logic();
		setTile(tile03, new Position(0, 3));
		updateBoard();
	}

	@FXML
	private void markTile05() throws IOException {
		controller.getGameBoard().getFirstRow().get(5).mark();
		logic();
		setTile(tile05, new Position(0, 5));
		updateBoard();
	}

	@FXML
	private void markTile07() throws IOException {
		controller.getGameBoard().getFirstRow().get(7).mark();
		logic();
		setTile(tile07, new Position(0, 7));
		updateBoard();
	}


	@FXML
	private void markTile10() throws IOException {
		controller.getGameBoard().getSecondRow().get(0).mark();
		logic();
		setTile(tile10, new Position(1, 0));
		updateBoard();
	}

	@FXML
	private void markTile11() throws IOException {
		controller.getGameBoard().getSecondRow().get(1).mark();
		logic();
		setTile(tile11, new Position(1, 1));
		updateBoard();
	}

	@FXML
	private void markTile12() throws IOException {
		controller.getGameBoard().getSecondRow().get(2).mark();
		logic();
		setTile(tile12, new Position(1, 2));
		updateBoard();
	}

	@FXML
	private void markTile13() throws IOException {
		controller.getGameBoard().getSecondRow().get(3).mark();
		logic();
		setTile(tile13, new Position(1, 3));
		updateBoard();
	}

	@FXML
	private void markTile14() throws IOException {
		controller.getGameBoard().getSecondRow().get(4).mark();
		logic();
		setTile(tile14, new Position(1, 4));
		updateBoard();
	}

	@FXML
	private void markTile15() throws IOException {
		controller.getGameBoard().getSecondRow().get(5).mark();
		logic();
		setTile(tile15, new Position(1, 5));
		updateBoard();
	}

	@FXML
	private void markTile16() throws IOException {
		controller.getGameBoard().getSecondRow().get(6).mark();
		logic();
		setTile(tile16, new Position(1, 6));
		updateBoard();
	}

	@FXML
	private void markTile17() throws IOException {
		controller.getGameBoard().getSecondRow().get(7).mark();
		logic();
		setTile(tile17, new Position(1, 7));
		updateBoard();
	}

	@FXML
	private void markTile18() throws IOException {
		controller.getGameBoard().getSecondRow().get(8).mark();
		logic();
		setTile(tile18, new Position(1, 8));
		updateBoard();
	}

	@FXML
	private void markTile19() throws IOException {
		controller.getGameBoard().getSecondRow().get(9).mark();
		logic();
		setTile(tile19, new Position(1, 9));
		updateBoard();
	}

}
