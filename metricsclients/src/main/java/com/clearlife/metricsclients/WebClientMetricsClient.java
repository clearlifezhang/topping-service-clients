package com.clearlife.metricsclients;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Log4j2
public class WebClientMetricsClient {

    private WebClient webClient;

    public WebClientMetricsClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<ToppingMetrics> metrics() {
        return webClient.get()
                .uri("http://localhost:8080/metrics")
                .retrieve()
                .bodyToFlux(ToppingMetrics.class)
                .doOnError(IOException.class, e->log.error(e.getMessage()));
        // return Flux.fromArray(new ToppingMetrics[0]);
    }
}
