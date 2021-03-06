package uk.co.redsoft.ports.data;

import uk.co.redsoft.domain.Price;
import uk.co.redsoft.domain.PriceRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPriceRepository implements PriceRepository {

    private Map<String, Price> store = new ConcurrentHashMap<>();

    {
        Price price = new Price(1.0, "GBP");
        store.put("ff1c10d7-96b3-427c-b16e-db82705121fa", price);
    }

    @Override
    public Price getPriceForGame(String gameId) {
        return store.get(gameId);
    }
}
