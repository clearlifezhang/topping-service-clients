package com.clearlife.metricsclients;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

class WebClientMetricsClientIntegrationTest {
    private WebClient webClient = WebClient.builder().build();

    @Test
    void shouldRetrieveMetricsFromTheService() {
        //given
        WebClientMetricsClient webClientMetricsClient = new WebClientMetricsClient(webClient);
        //when
        Flux<ToppingMetrics> metricsFlux = webClientMetricsClient.metrics();
        //then
        Assertions.assertNotNull(metricsFlux);
        Flux<ToppingMetrics> fiveMetrics = metricsFlux.take(5);
        Assertions.assertEquals(5, fiveMetrics.count().block());
        Assertions.assertTrue(fiveMetrics.blockFirst().getTotalCountPerTopping() >= fiveMetrics.blockFirst().getUniqueCountPerTopping());
    }
}