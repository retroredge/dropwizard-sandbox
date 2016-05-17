package uk.co.redsoft.scratch;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import uk.co.redsoft.ports.http.GameResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class TestClient {

    public static void main(String[] args) throws IOException {
        getWithJerseyClient();
        getWithApacheClient();
    }

    private static void getWithApacheClient() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpUriRequest getRequest = new HttpGet("http://localhost:8080/games/1");
        HttpResponse response = httpClient.execute(getRequest);

        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    private static void getWithJerseyClient() {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target("http://localhost:8080")
                .path("games/1")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        System.out.println(response.readEntity(GameResponse.class));
        response.close();
    }
}
