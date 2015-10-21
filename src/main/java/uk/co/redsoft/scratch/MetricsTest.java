package uk.co.redsoft.scratch;

import com.codahale.metrics.*;

import java.util.concurrent.TimeUnit;

public class MetricsTest {

    public static void main(String[] args) {

        MetricRegistry metricsRegistry = new MetricRegistry();

        // Register a Meter
        Meter requestsMetric = metricsRegistry.meter("dropwizard-sandbox.requestsMetric");

        // Register a Gauge
        metricsRegistry.register("dropwizard-sandbox.gauge", new Gauge<Integer>() {

            @Override
            public Integer getValue() {
                return 92;
            }
        });

        // Register a Counter
        Counter counter = metricsRegistry.counter("dropwizard-sandbox.counter");

        // Register a histogram
        Histogram histogram = metricsRegistry.histogram("dropwizard-sandbox.histogram");

        // Register a Timer
        Timer timer = metricsRegistry.timer("dropwizard-sandbox.timer");

        // Add a console reporter so we can see what's going on via console
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metricsRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);

        final JmxReporter jmxReporter = JmxReporter.forRegistry(metricsRegistry)
                .build();
        jmxReporter.start();

        // Do some work...
        int i = 0;
        try {

            while (true) {
                Timer.Context timerContext = timer.time();
                requestsMetric.mark();
                counter.inc();
                histogram.update(i);
                Thread.sleep(5000);
                i++;
                timerContext.stop();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
