package sample;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
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

    DB db = null;

    @FXML
    void initialize() {
        db = new DB();

        // Обработчик события. Сработает при нажатии на кнопку
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Проверяем является ли поле заполненным
                    if(!textField.getText().trim().equals("")) {
                        // Вызываем метод из класса DB
                        // через этот метод будет добавлено новое задание
                        db.insertTask(textField.getText());
                        loadInfo(); // Метод для подгрузки заданий внутрь программы
                        textField.setText(""); // Очищаем поле
                    }
                } catch (SQLException e) { // Отслеживаем ошибки
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // Метод для подгрузки заданий внутрь программы
        loadInfo();
    }

    // Метод для подгрузки заданий внутрь программы
    void loadInfo() {
        try {
            // Сначала очищаем от прошлых значений
            allThings.getChildren().clear();

            // Получаем все задания из базы данных
            ArrayList<String> tasks = db.getTasks();
            for(int i = 0; i < tasks.size(); i++) // Перебираем их через цикл
                // Добавляем каждое задание в объект VBox (allThings)
                allThings.getChildren().add( new Label( tasks.get(i)));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}