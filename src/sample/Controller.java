package sample;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import sample.db.DB;

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
    private VBox allThings;

    @FXML
    private Button clearButton;

    DB db = null;

    @FXML
    void initialize() {
        db = new DB();
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                if (!textField.getText().trim().equals("")) {
                    db.insertTask(textField.getText());
                    loadInfo();
                    textField.setText("");
                    loadInfo();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        clearButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                db.clearTable();
                loadInfo();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        loadInfo();
    }

    void loadInfo() {
        try {
            allThings.getChildren().clear();
            ArrayList<String> tasks = db.getTasks();
            for (String task : tasks) allThings.getChildren().add(new Label(task));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}