package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML // fx:id="btnTuttiVicini"
    private Button btnTuttiVicini; // Value injected by FXMLLoader
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;

	Model model;
	@FXML
	void doReset(ActionEvent event) {
		txtResult.clear();
		inputNumeroLettere.clear();
		inputParola.clear();
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {

		try {
			int numeroLettere = Integer.parseInt(inputNumeroLettere.getText());
			List<String> result = model.createGraph(numeroLettere);
			if(result!=null){
				txtResult.setText("Grafo creato, ci sono "+result.size()+" parole di "+numeroLettere+" lettere.");
			}
			
			
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
		
	}

	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		
		try {
			txtResult.setText("MAX- "+model.findMaxDegree());

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		
		try {
			String parola = inputParola.getText();
			txtResult.setText("Vicini di "+parola+": "+model.displayNeighbours(parola));
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}
	
	@FXML
	void doTrovaTuttiVicini(ActionEvent event) {
		try {
			String parola = inputParola.getText();
			txtResult.setText("Parole raggiungibili da "+parola+": "+model.displayAllNeighbours(parola));
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}

	}


	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTuttiVicini != null : "fx:id=\"btnTuttiVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
	}
	
	public void setModel(Model model){
		this.model = model;
	}
}