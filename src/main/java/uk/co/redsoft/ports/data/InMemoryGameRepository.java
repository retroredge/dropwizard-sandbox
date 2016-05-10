package uk.co.redsoft.ports.data;

import uk.co.redsoft.domain.Game;
import uk.co.redsoft.domain.GameRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryGameRepository implements GameRepository {

    private Map<String, Game> store = new ConcurrentHashMap<>();

    {
        Game game = new Game("1", "Game 1");
        store.put(game.getId(), game);
    }

    @Override
    public Game getById(String id) {
        return store.get(id);
    }
}
