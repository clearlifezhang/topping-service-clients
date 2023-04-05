package com.clearlife.metricsclients;

import reactor.core.publisher.Flux;

public interface MetricsClient {
    Flux<ToppingMetrics> metrics(String toppingName);
}
