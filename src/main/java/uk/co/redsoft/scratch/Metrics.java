package uk.co.redsoft.scratch;

import com.codahale.metrics.*;

import java.util.concurrent.TimeUnit;

public class Metrics {

    public static void main(String[] args) {
        MetricRegistry metricsRegistry = new MetricRegistry();
        Meter requestsMetric = metricsRegistry.meter("dropwizard-sandbox.requestsMetric");
        metricsRegistry.register("dropwizard-sandbox.gauge", (Gauge<Integer>) () -> 92);
        Counter counter = metricsRegistry.counter("dropwizard-sandbox.counter");
        Histogram histogram = metricsRegistry.histogram("dropwizard-sandbox.histogram");
        Timer timer = metricsRegistry.timer("dropwizard-sandbox.timer");

        ConsoleReporter reporter = ConsoleReporter.forRegistry(metricsRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);

        final JmxReporter jmxReporter = JmxReporter.forRegistry(metricsRegistry).build();
        jmxReporter.start();

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
