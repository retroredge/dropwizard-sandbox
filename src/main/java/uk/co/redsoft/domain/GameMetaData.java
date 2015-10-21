package uk.co.redsoft.domain;

import com.google.common.base.Objects;

public class GameMetaData {

    private final Game game;
    private final Price price;

    public GameMetaData(Game game, Price price) {
        this.game = game;
        this.price = price;
    }

    public Game getGame() {
        return game;
    }

    public Price getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameMetaData that = (GameMetaData) o;
        return Objects.equal(game, that.game) &&
                Objects.equal(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(game, price);
    }
}
