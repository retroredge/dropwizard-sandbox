package uk.co.redsoft.domain;

import com.google.common.base.Objects;

public class Game {

    private final String id;
    private final String name;

    public Game(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equal(id, game.id) &&
                Objects.equal(name, game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name);
    }
}
