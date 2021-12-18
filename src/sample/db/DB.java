package sample.db;

import java.sql.*;
import java.util.ArrayList;

public class DB {

    public void insertTask(String task) throws SQLException {
        String sql = "INSERT INTO tab (did) VALUES(?)";

        PreparedStatement prSt = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydeals", "postgres", "123").prepareStatement(sql);
        prSt.setString(1, task);

        prSt.executeUpdate();
    }

    public void clearTable() throws SQLException {
        String sql = "DELETE FROM tab";

        PreparedStatement prSt = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydeals", "postgres", "123").prepareStatement(sql);

        prSt.executeUpdate();
    }

    public ArrayList<String> getTasks() throws SQLException {
        String sql = "SELECT * FROM tab ORDER BY id DESC";
        Statement statement = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydeals", "postgres", "123").createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> tasks = new ArrayList<>();
        while (res.next()) {
            tasks.add(res.getString("did"));
        }
        return tasks;
    }
}
