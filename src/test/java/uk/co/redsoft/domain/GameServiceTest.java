package uk.co.redsoft.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameServiceTest {

    @Test
    public void getGameData() {
        // given
        GameRepository gameRepository = mock(GameRepository.class);
        PriceRepository priceRepository = mock(PriceRepository.class);

        GameService gameService = new GameService(gameRepository, priceRepository);

        Game expectedGame = new Game("123", "Pong");
        when(gameRepository.getById("123")).thenReturn(expectedGame);

        Price expectedPrice = new Price(0.99, "GBP");
        when(priceRepository.getPriceForGame("123")).thenReturn(expectedPrice);

        GameMetaData expectedGameMetaData = new GameMetaData(expectedGame, expectedPrice);

        // when
        GameMetaData gameMetaData = gameService.getGame("123");

        // then
        assertThat(gameMetaData).isEqualTo(expectedGameMetaData);
    }
}
