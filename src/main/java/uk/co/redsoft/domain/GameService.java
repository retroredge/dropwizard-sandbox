package uk.co.redsoft.domain;

public class GameService {

    private final GameRepository gameRepository;
    private final PriceRepository priceRepository;

    public GameService(GameRepository gameRepository, PriceRepository priceRepository) {

        this.gameRepository = gameRepository;
        this.priceRepository = priceRepository;
    }

    public GameMetaData getGame(String gameId) {

        final Game game = gameRepository.getById(gameId);
        final Price price = priceRepository.getPriceForGame(gameId);

        return new GameMetaData(game, price);
    }
}
