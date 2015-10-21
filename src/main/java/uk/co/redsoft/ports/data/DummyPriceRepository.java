package uk.co.redsoft.ports.data;

import uk.co.redsoft.domain.Price;
import uk.co.redsoft.domain.PriceRepository;

public class DummyPriceRepository implements PriceRepository {

    @Override
    public Price getPriceForGame(String gameId) {
        return new Price(1.0, "GBP");
    }
}
