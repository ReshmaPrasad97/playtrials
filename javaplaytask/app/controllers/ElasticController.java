package controllers;

import com.google.inject.Inject;
import org.elasticsearch.client.RestClient;
import play.mvc.Result;

import static play.mvc.Results.ok;

public class ElasticController {

    @Inject
    private RestClient restClient;

    public Result connection() {
        return ok("success");
    }

}
