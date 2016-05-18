package uk.co.redsoft.integration;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import uk.co.redsoft.AppConfig;
import uk.co.redsoft.GameApplication;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameMetaDataTest {

    @ClassRule
    public static DropwizardAppRule<AppConfig> APPLICATION
            = new DropwizardAppRule<>(GameApplication.class, "dropwizard-sandbox.yml");

    private Client client;

    @Before
    public void setupClient() {
        client = new JerseyClientBuilder(APPLICATION.getEnvironment()).build("test client");
    }

    @Test
    public void retrievesGameMetaData() {
        // when
        Response response = getGame("ff1c10d7-96b3-427c-b16e-db82705121fa");

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);

        Map<String, Object> gameData = response.readEntity(new GenericType<Map<String, Object>>() {});
        assertThat(gameData.get("id")).isNotNull();
        assertThat(gameData.get("name")).isEqualTo("Game 1");
        assertThat(gameData.get("price")).isEqualTo(1.0D);
        assertThat(gameData.get("currency")).isEqualTo("GBP");
    }

    private Response getGame(String gameId) {
        return client.target(
                String.format("http://localhost:%d/games/%s", APPLICATION.getLocalPort(), gameId))
                .request()
                .get();
    }
}
