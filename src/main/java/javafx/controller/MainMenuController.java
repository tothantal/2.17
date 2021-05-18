package javafx.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainMenuController {


	@FXML
	private Button startButton;

	@FXML
	private Button scoreButton;

	@FXML
	private TextField playerField;

	@FXML
	private Label nameLabel;

	@FXML
	private void launchGame() throws IOException {

		if (!playerField.getText().isEmpty()) {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/board.fxml"));
			Parent root = loader.load();



			BoardController boardController = loader.getController();
			boardController.setPlayerName(playerField.getText());

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Board");
			stage.show();

		} else {
			nameLabel.setText("Please enter your name!");
			nameLabel.setTextFill(Color.RED);
		}


	}

	@FXML
	private void showScore() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/score.fxml"));
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Highscore");
		stage.show();
	}

}
