package uk.co.redsoft;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import uk.co.redsoft.domain.GameRepository;
import uk.co.redsoft.domain.GameService;
import uk.co.redsoft.domain.PriceRepository;
import uk.co.redsoft.ports.data.InMemoryGameRepository;
import uk.co.redsoft.ports.data.InMemoryPriceRepository;
import uk.co.redsoft.ports.http.GameResource;

public class GameApplication extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        new GameApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-sandbox";
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
    }

    @Override
    public void run(AppConfig configuration, Environment environment) throws Exception {
        final GameRepository gameRepository = new InMemoryGameRepository();
        final PriceRepository priceRepository = new InMemoryPriceRepository();
        final GameResource gameResource = new GameResource(new GameService(gameRepository, priceRepository));

        final GameHealthCheck healthCheck = new GameHealthCheck();
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(gameResource);
    }
}
