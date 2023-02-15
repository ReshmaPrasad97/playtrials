package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import config.ConnectionClient;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import play.mvc.Http;
import play.mvc.Result;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static play.mvc.Results.ok;

public class ProductService {
    @Inject
    private ConnectionClient connectionClient;



    public Result addProduct(Http.Request request) throws IOException {
        JsonNode productJson = request.body().asJson();
        int productId = productJson.get("productid").asInt();
        String productName = productJson.get("productname").asText();
        int specId = productJson.get("specid").asInt();
        String productSpec = productJson.get("productspec").asText();
        Map<String, Object> productMap = new HashMap<>();
        productMap.put("productid", productId);
        productMap.put("productname", productName);
        productMap.put("specid", specId);
        IndexRequest indexRequest = new IndexRequest("product", "_doc").source(productMap);
        connectionClient.getRestHighLevelClient().index(indexRequest, RequestOptions.DEFAULT);

        Map<String, Object> specJson = new HashMap<>();
        specJson.put("specid", specId);
        specJson.put("productspec", productSpec);

        IndexRequest indexRequest1 = new IndexRequest("productspec", "_doc").source(specJson);
        connectionClient.getRestHighLevelClient().index(indexRequest1, RequestOptions.DEFAULT);


        return ok(productJson);
    }

}

