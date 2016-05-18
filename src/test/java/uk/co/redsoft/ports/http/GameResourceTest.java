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
        GameMetaData expectedGameMetaData =
                new GameMetaData(new Game("ff1c10d7-96b3-427c-b16e-db82705121fa", "Pong"), new Price(0.99, "GBP"));
        when(gameService.getGameMetaData("ff1c10d7-96b3-427c-b16e-db82705121fa")).thenReturn(expectedGameMetaData);
        GetGameResponse expectedGetGameResponse = GetGameResponse.from(expectedGameMetaData);

        // when
        GetGameResponse actualGame = resources.client()
                .target("/games/ff1c10d7-96b3-427c-b16e-db82705121fa")
                .request().get(GetGameResponse.class);

        // then
        assertThat(actualGame).isEqualTo(expectedGetGameResponse);
    }

}