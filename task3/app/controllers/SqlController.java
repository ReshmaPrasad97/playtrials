package controllers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import data.MysqlClient;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import scala.xml.factory.NodeFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static play.mvc.Results.ok;

public class SqlController extends Controller {
    @Inject
    private MysqlClient mysqlClient;

    public Result getData() {
        return ok(mysqlClient.getCountrymap().toString());

    }
}

//    public Result getData() throws SQLException {
//
//        PreparedStatement statement = mysqlClient.getConnection().prepareStatement("select * from country ");
//        ArrayNode result = JsonNodeFactory.instance.arrayNode();
//        ResultSet resultSet = statement.executeQuery();
//        while (resultSet.next()){
//            int id = resultSet.getInt("id");
//            String name = resultSet.getString("name");
//            result.add(id);
//            result.add(name);
////            System.out.println(result);
//        }
//        return ok(result);
//
//    }
//
//}

