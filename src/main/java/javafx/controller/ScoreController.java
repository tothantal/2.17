package javafx.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import boardgame.model.Highscore;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScoreController {

	Highscore scores = new Highscore();


	@FXML
	Label scoreText;

	@FXML
	private void initialize() throws IOException, JAXBException {

		File XMLFile = new File("score.xml");
		if (!XMLFile.exists()) {
			XMLFile.createNewFile();
			this.scoreText.setText("No scores yet");
		}

		this.scores.fromXML();
		this.scoreText.setText(scores.toString());

	}

}
