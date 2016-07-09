package uk.co.redsoft.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameServiceTest {
    private static final String GAME_UUID = "ff1c10d7-96b3-427c-b16e-db82705121fa";

    private GameRepository gameRepository = mock(GameRepository.class);
    private PriceRepository priceRepository = mock(PriceRepository.class);
    private GameService gameService = new GameService(gameRepository, priceRepository);

    @Test
    public void getGameData() {
        // given
        Game expectedGame = new Game(GAME_UUID, "Pong");
        when(gameRepository.getById(GAME_UUID)).thenReturn(expectedGame);

        Price expectedPrice = new Price(0.99, "GBP");
        when(priceRepository.getPriceForGame(GAME_UUID)).thenReturn(expectedPrice);

        GameMetaData expectedGameMetaData = new GameMetaData(expectedGame, expectedPrice);

        // when
        GameMetaData gameMetaData = gameService.getGameMetaData(GAME_UUID);

        // then
        assertThat(gameMetaData).isEqualTo(expectedGameMetaData);
    }
}
