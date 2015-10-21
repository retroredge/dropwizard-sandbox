package uk.co.redsoft;

import com.codahale.metrics.health.HealthCheck;

public class GameHealthCheck extends HealthCheck {

    public GameHealthCheck() {
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
