package uk.co.redsoft.integration;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;
import uk.co.redsoft.AppConfig;
import uk.co.redsoft.GameApplication;
import uk.co.redsoft.domain.Game;
import uk.co.redsoft.domain.GameMetaData;
import uk.co.redsoft.domain.Price;
import uk.co.redsoft.ports.http.GameResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class GameMetaDataTest {

    @ClassRule
    public static DropwizardAppRule<AppConfig> APPLICATION
            = new DropwizardAppRule<>(GameApplication.class, "dropwizard-sandbox.yml");

    @Test
    public void retrievesGameMetaData() {
        Client client = new JerseyClientBuilder(APPLICATION.getEnvironment()).build("test client");

        Response response = client.target(
                String.format("http://localhost:%d/games/1", APPLICATION.getLocalPort()))
                .request()
                .get();

        GameMetaData expectedGameMetaData = new GameMetaData(new Game("1", "Game 1"), new Price(1.0, "GBP"));
        GameResponse expectedGameResponse = GameResponse.from(expectedGameMetaData);

        assertThat(response.readEntity(GameResponse.class)).isEqualTo(expectedGameResponse);
    }
}
