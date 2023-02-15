package config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ConnectionClient {
    private RestHighLevelClient restHighLevelClient;
    public ConnectionClient(){
        restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        System.out.println("Sucessfully Connected");

    }

    public RestHighLevelClient getRestHighLevelClient() {
        return restHighLevelClient;
    }
}
