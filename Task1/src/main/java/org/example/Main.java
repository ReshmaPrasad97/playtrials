package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Main object = new Main();
        Connection connection = object.sqlConnection();
//        object.createPreparedStatement(connection);
//        object.getStudent(connection);

    }
    public Connection sqlConnection() throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement", "root", "password");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement", "root", "password");
        return connection;
    }
    //
    public void createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO student (id,name,address) VALUES (?, ?, ?)");
        ps.setInt(1, 78);
        ps.setString(2, "Renu");
        ps.setString(3, "Address");
        ps.executeUpdate();
        System.out.println("Success");

    }
    public  PreparedStatement getStudent(Connection connection) throws SQLException {
        List<Object> stud = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT id,name,address FROM student where id =11");
        ResultSet rs = stmt.executeQuery();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                stud.add(id);
                stud.add(name);
                stud.add(address);
                System.out.println(stud);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stmt;
    }




}