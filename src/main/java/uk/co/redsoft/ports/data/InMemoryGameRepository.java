package uk.co.redsoft.ports.data;

import uk.co.redsoft.domain.Game;
import uk.co.redsoft.domain.GameRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryGameRepository implements GameRepository {

    private Map<String, Game> store = new ConcurrentHashMap<>();

    {
        Game game = new Game("ff1c10d7-96b3-427c-b16e-db82705121fa", "Game 1");
        store.put(game.getId(), game);
    }

    @Override
    public Game getById(String id) {
        return store.get(id);
    }
}
