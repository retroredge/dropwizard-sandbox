package uk.co.redsoft.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import uk.co.redsoft.AppConfig;
import uk.co.redsoft.GameApplication;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameMetaDataTest {
    @ClassRule
    public static DropwizardAppRule<AppConfig> APPLICATION
            = new DropwizardAppRule<>(GameApplication.class, "dropwizard-sandbox.yml");

    private static Client client;

    @BeforeClass
    public static void setupClient() {
        client = new JerseyClientBuilder(APPLICATION.getEnvironment()).build("test client");
    }

    @Test
    public void retrievesGameMetaData() {
        // when
        Response response = getGame("ff1c10d7-96b3-427c-b16e-db82705121fa");

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);

        Map<String, Object> gameData = response.readEntity(new GenericType<Map<String, Object>>() {});
        assertThat(gameData).containsKey("id");
        assertThat(gameData).containsEntry("name", "Game 1");
        assertThat(gameData).containsEntry("price", 1.0D);
        assertThat(gameData).containsEntry("currency", "GDP");
    }

    @Test
    public void createPremiumPriceGameGame() throws JsonProcessingException {
        // when
        Response response = createGame();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_CREATED);
    }

    private Response getGame(String gameId) {
        return client.target(String.format("http://localhost:%d/games/%s", APPLICATION.getLocalPort(), gameId))
                .request()
                .get();
    }

    private Response createGame() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        return client
                .target(String.format("http://localhost:%d", APPLICATION.getLocalPort()))
                .path("games")
                .request()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post(Entity.json(createJsonPayload()));
    }

    private static String createJsonPayload() throws JsonProcessingException {
        Map<String, Object> values = ImmutableMap.of("name", "game-name", "priceCode", "premium");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(values);
    }

}
