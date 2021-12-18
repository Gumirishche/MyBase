package sample.db;

import java.sql.*;
import java.util.ArrayList;

public class DB {

    public void insertTask(String task) throws SQLException, ClassNotFoundException{
        String sql = "INSERT INTO mydealstable (deal) VALUES(?)";

        PreparedStatement prSt = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydeals","postgres","123").prepareStatement(sql);
        prSt.setString(1,task);

        prSt.executeUpdate();
    }

    public ArrayList<String> getTasks() throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM mydealstable ORDER BY id DESC";
        Statement statement= DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydeals","postgres","123").createStatement();
        ResultSet res =statement.executeQuery(sql);

        ArrayList<String> tasks=new ArrayList<>();
        while (res.next()){
            tasks.add(res.getString("deal"));
        }
        return tasks;
    }
}
