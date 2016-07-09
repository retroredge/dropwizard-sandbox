package uk.co.redsoft.domain;

import java.util.UUID;

public class GameService {

    private final GameRepository gameRepository;
    private final PriceRepository priceRepository;

    public GameService(GameRepository gameRepository, PriceRepository priceRepository) {
        this.gameRepository = gameRepository;
        this.priceRepository = priceRepository;
    }

    public GameMetaData getGameMetaData(String gameId) {
        final Game game = gameRepository.getById(gameId);
        final Price price = priceRepository.getPriceForGame(gameId);

        return new GameMetaData(game, price);
    }

    public UUID createGame(Game game) {
        return null;
    }
}
