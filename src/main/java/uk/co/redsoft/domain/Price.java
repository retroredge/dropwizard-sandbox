package uk.co.redsoft.domain;

import com.google.common.base.Objects;

public class Price {

    private final double price;
    private final String currency;

    public Price(double price, String currency) {
        this.price = price;
        this.currency = currency;
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
        Price price1 = (Price) o;
        return Objects.equal(price, price1.price) &&
                Objects.equal(currency, price1.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(price, currency);
    }
}
