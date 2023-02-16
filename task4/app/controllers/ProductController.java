package controllers;

import com.google.inject.Inject;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.ProductService;
import service.SqlProductService;
import java.io.IOException;
import java.sql.SQLException;

public class ProductController extends Controller {
    @Inject
    private ProductService productService;
    @Inject
    private SqlProductService sqlProductService;
    public Result addProductData(Http.Request request) throws IOException {
        productService.addProduct(request);
        return ok("Data Inserted");
    }
    public Result getProductData(Integer id) throws IOException {
        return ok(productService.getById(id).toString());
    }

    public Result addProduct(Http.Request request) throws SQLException {
        sqlProductService.addData(request);
        return ok("Data succesfully inserted");
    }

    public Result getData(Integer specid) throws SQLException {
        String result =sqlProductService.getData(specid);
        return ok(result);
    }
    public Result getProduct(Integer specid) throws SQLException {
        String result = sqlProductService.getProduct(specid).toString();
        return ok(result);
    }
}
