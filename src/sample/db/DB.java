package sample.db;

import java.sql.*;
import java.util.ArrayList;

public class DB {

    public void insertTask(String task, String login) throws SQLException {
        String sql = "INSERT INTO " + login + " (thing) VALUES(?)";

        PreparedStatement prSt = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydeals", "postgres", "123").prepareStatement(sql);
        prSt.setString(1, task);
        System.out.println(prSt);

        prSt.executeUpdate();
    }

    public void clearTable(String login) throws SQLException {
        String sql = "DELETE FROM " + login;

        PreparedStatement prSt = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydeals", "postgres", "123").prepareStatement(sql);

        prSt.executeUpdate();
    }

    public ArrayList<String> getTasks(String login) throws SQLException {
        String sql = "SELECT * FROM " + login + " ORDER BY id DESC";
        Statement statement = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydeals", "postgres", "123").createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> tasks = new ArrayList<>();
        while (res.next()) {
            tasks.add(res.getString("thing"));
        }
        return tasks;
    }

    public void reg(String login) throws SQLException {
        String sql1 = "INSERT INTO users (login) VALUES(?)";

        PreparedStatement prSt1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydeals", "postgres", "123").prepareStatement(sql1);
        prSt1.setString(1, login);
        System.out.println(prSt1);

        prSt1.executeUpdate();

        String sql2 = "CREATE TABLE "+login+" (id BIGSERIAL NOT NULL PRIMARY KEY, THING VARCHAR(50) NOT NULL)";

        PreparedStatement prSt2 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydeals", "postgres", "123").prepareStatement(sql2);
        System.out.println(prSt2);
        prSt2.executeUpdate();
    }

    public boolean login(String login) throws SQLException {
        boolean b = false;
        System.out.println(login);
        Statement statement = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydeals", "postgres", "123").createStatement();
        ResultSet res = statement.executeQuery("SELECT * FROM users WHERE login like'" + login + "'");
        while (res.next()) {
            System.out.println(res.getString("id"));
            b = !res.getString("id").equals(" ");
        }
        return b;
    }

}
