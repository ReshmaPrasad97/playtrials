package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import config.SqlClient;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static play.mvc.Results.ok;

public class SqlProductService {
    
    @Inject
    private SqlClient sqlClient;

    public Result addData(Http.Request request) throws SQLException {
        JsonNode json = request.body().asJson();
        int productid = json.get("productid").asInt();
        String productname = json.get("productname").asText();
        int specid = json.get("specid").asInt();
        String productspec = json.get("productspec").asText();

        PreparedStatement preparedStatement = sqlClient.getConnection().prepareStatement("insert into product(productid,productname,specid) values (?,?,?)");
        preparedStatement.setInt(1,productid);
        preparedStatement.setString(2,productname);
        preparedStatement.setInt(3,specid);
        preparedStatement.executeUpdate();
        System.out.println("data inserted to product table");

        PreparedStatement statement = sqlClient.getConnection().prepareStatement("insert into productspec(specid,productspec) values (?,?)");
        statement.setInt(1,specid);
        statement.setString(2,productspec);
        statement.executeUpdate();
        System.out.println("data inserted to productspec table");

        return ok("Data inserted");
    }
    public String getData(int specid) throws SQLException {
        PreparedStatement preparedStatement1 = sqlClient.getConnection().prepareStatement("select * from productspec INNER JOIN product on productspec.specid = product.specid ");

        ObjectNode result = Json.newObject();
        //        preparedStatement1.setInt(1,specid);
        ResultSet resultSet = preparedStatement1.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("specid");
            String productspec =resultSet.getString("productspec");
            int pid = resultSet.getInt("productid");
            String pname = resultSet.getString("productname");
            result.put("specid",id);
            result.put("productspec",productspec);
            result.put("productid",pid);
            result.put("productname",pname);
        }
        return result.toString();

    }
    public String getProduct(int specid) throws SQLException {
        PreparedStatement preparedStatement1 = sqlClient.getConnection().prepareStatement("select * from product where specid =? ");

        ObjectNode result = Json.newObject();
        preparedStatement1.setInt(1,specid);
        ResultSet resultSet = preparedStatement1.executeQuery();
        while (resultSet.next()){

            int id = resultSet.getInt("productid");
            String pname = resultSet.getString("productname");
            int sid = resultSet.getInt("specid");
            result.put("productid",id);
            result.put("productname",pname);
            result.put("specid",sid);

        }
        return result.toString();

    }

}
