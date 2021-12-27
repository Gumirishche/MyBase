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

import javax.swing.*;

public class LoginCon {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private Button vButton;

    @FXML
    private Button regButton;

    DB db = null;
    public static String login="";
    public static Stage stage = new Stage();
    public static int numL = 0;

    @FXML
    void initialize() {
        db = new DB();
        vButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                numL=0;
                login=loginField.getText();
                if (!loginField.getText().trim().equals("")) {
                    if(db.login(login)){
                        Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/sample.fxml"));
                        if(Controller.num==0){
                        Main.stage.close();
                        }
                        else {
                            Controller.stage.close();
                        }
                        stage.setTitle("Список дел");
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Такого пользователя не существует!");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Поле пустое");
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });

        regButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                numL=1;
                Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/registration.fxml"));
                if(Controller.num==0) {
                    Main.stage.close();
                }
                else {
                    Controller.stage.close();
                }
                stage.setTitle("Регистрация");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}