package uk.co.redsoft.scratch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import uk.co.redsoft.ports.http.GetGameResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

public class TestClient {

    public static void main(String[] args) throws IOException {
        getWithApacheClient();
        getWithJerseyClient();

        postWithApacheClient();
        postWithJerseyClient();
    }

    private static void getWithApacheClient() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet("http://localhost:8080/games/ff1c10d7-96b3-427c-b16e-db82705121fa");
        HttpResponse response = httpClient.execute(getRequest);

        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    private static void getWithJerseyClient() {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target("http://localhost:8080")
                .path("games/ff1c10d7-96b3-427c-b16e-db82705121fa")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        System.out.println(response.readEntity(GetGameResponse.class));
        response.close();
    }

    private static void postWithApacheClient() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost("http://localhost:8080/games");

        StringEntity entity = new StringEntity(getJsonPayload(), ContentType.create(MediaType.APPLICATION_JSON, "UTF-8"));
        postRequest.setEntity(entity);
        HttpResponse response = httpClient.execute(postRequest);

        System.out.println(response.getStatusLine().getStatusCode());
    }

    private static void postWithJerseyClient() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target("http://localhost:8080")
                .path("games")
                .request()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post(Entity.json(getJsonPayload()));
        System.out.println(response.getStatus());
        System.out.println(response.getStatusInfo());
        response.close();
    }

    private static String getJsonPayload() throws JsonProcessingException {
        Map<String, Object> values = ImmutableMap.of("name", "game-name", "priceCode", "premium");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(values);
    }
}
