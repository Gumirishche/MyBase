package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField textField;

    @FXML
    private VBox VBox;

    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert textField != null : "fx:id=\"textField\" was not injected: check your FXML file 'sample.fxml'.";
        assert VBox != null : "fx:id=\"VBox\" was not injected: check your FXML file 'sample.fxml'.";

    }
}