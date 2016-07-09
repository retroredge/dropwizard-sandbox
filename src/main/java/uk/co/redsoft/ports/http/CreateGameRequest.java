package uk.co.redsoft.ports.http;

import com.google.common.base.Objects;
import uk.co.redsoft.domain.Game;

public class CreateGameRequest {
    private String name;
    private String priceCode;

    public void setName(String name) {
        this.name = name;
    }

    public void setPriceCode(String priceCode) {
        this.priceCode = priceCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateGameRequest that = (CreateGameRequest) o;
        return Objects.equal(name, that.name) &&
                Objects.equal(priceCode, that.priceCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, priceCode);
    }

    @Override
    public String toString() {
        return "CreateGameRequest{" +
                "name='" + name + '\'' +
                ", priceCode='" + priceCode + '\'' +
                '}';
    }

    public Game toGame() {
        return new Game(name);
    }
}
