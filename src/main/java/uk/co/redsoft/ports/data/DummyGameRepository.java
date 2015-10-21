package uk.co.redsoft.ports.data;

import uk.co.redsoft.domain.Game;
import uk.co.redsoft.domain.GameRepository;

public class DummyGameRepository implements GameRepository {

    @Override
    public Game getById(String id) {
        return new Game("1", "Dummy game");
    }
}
