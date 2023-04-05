package com.clearlife.metricsclients;

import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;

import java.io.IOException;
@Log4j2
public class RSocketMetricsClient implements MetricsClient {
    private RSocketRequester rSocketRequester;

    public RSocketMetricsClient(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }

    @Override
    public Flux<ToppingMetrics> metrics(String toppingName) {
        return rSocketRequester.route("toppingmetrics")
                .data(toppingName)
                .retrieveFlux(ToppingMetrics.class)
                .retry(5)
                .doOnError(IOException.class, e->log.error(e.getMessage()));
    }

}
