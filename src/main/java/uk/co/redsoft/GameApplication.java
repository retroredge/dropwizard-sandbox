package uk.co.redsoft;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import uk.co.redsoft.domain.GameService;
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
        final GameResource gameResource =
                new GameResource(new GameService(new InMemoryGameRepository(), new InMemoryPriceRepository()));
        environment.jersey().register(gameResource);

        final GameHealthCheck healthCheck = new GameHealthCheck();
        environment.healthChecks().register("template", healthCheck);
    }
}
