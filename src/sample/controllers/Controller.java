package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Main;
import sample.db.DB;

import javax.swing.*;

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

    @FXML
    private Button vButton;

    public static Stage stage = new Stage();
    public static int num = 0;
    DB db = null;

    @FXML
    void initialize() {
        db = new DB();
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                if (!textField.getText().trim().equals("")) {
                    db.insertTask(textField.getText(),LoginCon.login);
                    loadInfo();
                    textField.setText("");
                    loadInfo();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Поле пустое");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        clearButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                db.clearTable(LoginCon.login);
                loadInfo();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        vButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                num=1;
                Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/login.fxml"));
                if(LoginCon.numL==0) {
                    LoginCon.stage.close();
                }
                else {
                    RegistrationCon.stage.close();
                }
                stage.setTitle("Логин");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        loadInfo();
    }

    void loadInfo() {
        try {
            allThings.getChildren().clear();
            ArrayList<String> tasks = db.getTasks(LoginCon.login);
            for (String task : tasks) allThings.getChildren().add(new Label(task));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}