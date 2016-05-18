package uk.co.redsoft.ports.http;

import com.google.common.base.Objects;
import uk.co.redsoft.domain.GameMetaData;

public class GameResponse {

    private String id;
    private String name;
    private double price;
    private String currency;

    private GameResponse() {}

    private GameResponse(String id, String name, double price, String currency) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResponse that = (GameResponse) o;
        return Objects.equal(price, that.price) &&
                Objects.equal(id, that.id) &&
                Objects.equal(name, that.name) &&
                Objects.equal(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, price, currency);
    }

    @Override
    public String toString() {
        return "GameResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }

    public static GameResponse from(GameMetaData gameMetaData) {
        return new GameResponse(
                gameMetaData.getGame().getId(),
                gameMetaData.getGame().getName(),
                gameMetaData.getPrice().getPrice(),
                gameMetaData.getPrice().getCurrency());
    }
}
