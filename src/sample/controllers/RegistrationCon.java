package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;
import sample.db.DB;

public class RegistrationCon {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private Button vButton;

    DB db = null;
    public static String login = "";
    public static Stage stage = new Stage();

    @FXML
    void initialize() {
        db = new DB();
        vButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                login = loginField.getText();
                if (!loginField.getText().trim().equals("")) {
                    db.reg(login);
                    Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/login.fxml"));
                    StartCon.stage.close();
                    stage.setTitle("Логин");
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}