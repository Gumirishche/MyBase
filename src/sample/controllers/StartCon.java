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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;

public class StartCon {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button regButton;

    @FXML
    private Button vButton;

    public static Stage stage = new Stage();
    public static int num = 0;

    @FXML
    void initialize() {
        vButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                num=0;
                Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/login.fxml"));
                Main.stage.close();
                stage.setTitle("Логин");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        regButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                num=1;
                Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/registration.fxml"));
                Main.stage.close();
                stage.setTitle("Регистрация");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}