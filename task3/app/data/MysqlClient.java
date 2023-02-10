package data;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.google.inject.Inject;
import play.api.db.Database;

import java.sql.*;
import java.util.HashMap;

public class MysqlClient {

    private Connection connection;
    private HashMap<Integer,String> countrymap;




    public MysqlClient() throws SQLException {

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CountryManagement", "root", "password");
        System.out.println("Succesfully connected");
        HashMap<Integer,String> countrymap = new HashMap<>();
        PreparedStatement statement = connection.prepareStatement("select * from country ");
        statement.executeQuery();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            countrymap.put(id,name);

         }
        this.countrymap = countrymap;

    }
    public Connection getConnection() {
        return connection;
    }

    public HashMap<Integer, String> getCountrymap() {
        return countrymap;
    }
}
