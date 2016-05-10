package uk.co.redsoft.ports.http;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;
import uk.co.redsoft.domain.Game;
import uk.co.redsoft.domain.GameMetaData;
import uk.co.redsoft.domain.GameService;
import uk.co.redsoft.domain.Price;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameResourceTest {

    private static GameService gameService = mock(GameService.class);

    @ClassRule
    public static final ResourceTestRule resources =
            ResourceTestRule.builder()
                    .addResource(new GameResource(gameService))
                    .build();

    @Test
    public void getsGameMetaData() throws Exception {
        // given
        GameMetaData expectedGameMetaData = new GameMetaData(new Game("123", "Pong"), new Price(0.99, "GBP"));
        when(gameService.getGameMetaData("123")).thenReturn(expectedGameMetaData);
        GameResponse expectedGameResponse = GameResponse.from(expectedGameMetaData);

        // when
        GameResponse actualGame = resources.client()
                .target("/games/123")
                .request().get(GameResponse.class);

        // then
        assertThat(actualGame).isEqualTo(expectedGameResponse);
    }

}