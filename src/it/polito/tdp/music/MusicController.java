package it.polito.tdp.music;

import java.net.URL;
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.music.model.ArtistFrequency;
import it.polito.tdp.music.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MusicController {
	
	private Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Month> boxMese;

    @FXML
    private Button btnArtisti;

    @FXML
    private Button btnNazioni;

    @FXML
    private TextArea txtResult;

    @FXML
    void doDistanza(ActionEvent event) {
    	
    	Month m = boxMese.getValue() ;
    	if(m==null) {
    		txtResult.appendText("Devi selezionare un mese\n");
    		return ;
    	}

    	model.getTopCountries(m) ;
    	model.creaGrafo(m);
    	
    	int max = model.maxDistanzaCountry() ;
    	
    	txtResult.appendText(String.format("Massima distanza: %d\n", max));
    
    }

    @FXML
    void doElenco(ActionEvent event) {
    	Month m = boxMese.getValue() ;
    	if(m==null) {
    		txtResult.appendText("Devi selezionare un mese\n");
    		return ;
    	}
    	
    	List<ArtistFrequency> list = model.getTopArtists(m) ;
    	
    	if(list==null) {
    		txtResult.appendText("Errore nel database\n");
    		return ;
    	}
    	
    	for(ArtistFrequency f : list) {
    		txtResult.appendText(String.format("%-30s %4d\n",
    				f.getArtist(), f.getFreqency()));
    	}
    }
    
    public void setModel(Model model) {
    	this.model = model ;
    	
        boxMese.getItems().addAll(model.getMonths()) ;
    }

    @FXML
    void initialize() {
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'MusicA.fxml'.";
        assert btnArtisti != null : "fx:id=\"btnArtisti\" was not injected: check your FXML file 'MusicA.fxml'.";
        assert btnNazioni != null : "fx:id=\"btnNazioni\" was not injected: check your FXML file 'MusicA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MusicA.fxml'.";

    }
}
