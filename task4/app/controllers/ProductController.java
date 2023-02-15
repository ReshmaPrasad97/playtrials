package controllers;

import com.google.inject.Inject;
import org.elasticsearch.http.HttpRequest;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.twirl.api.Content;
import service.ProductService;

import java.io.IOException;

public class ProductController extends Controller {
    @Inject
    private ProductService productService;
    public Result addProductData(Http.Request request) throws IOException {
        productService.addProduct(request);
        return ok("Data Inserted");
    }
    public Result getProductData(Integer id) throws IOException {
        return ok(productService.getById(id).toString());
    }
}
