package com.clearlife.metricsclients;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
class RSocketMetricsClientIntegrationTest {
    @Autowired
    private RSocketRequester.Builder builder;

    private RSocketRequester createRSocketRequester() {
        return builder.connectTcp("localhost", 7070).block();
    }

    @Test
    void shouldRetrieveMetricsFromTheService() {
        //given
        RSocketMetricsClient rSocketMetricsClient = new RSocketMetricsClient(createRSocketRequester());
        //when
        Flux<ToppingMetrics> metricsFlux = rSocketMetricsClient.metrics("Bacon");
        //then
        Assertions.assertNotNull(metricsFlux);
        Flux<ToppingMetrics> fiveMetrics = metricsFlux.take(5);
        Assertions.assertEquals(5, fiveMetrics.count().block());
        Assertions.assertTrue(fiveMetrics.blockFirst().getTotalCountPerTopping() >= fiveMetrics.blockFirst().getUniqueUserCountPerTopping());
        StepVerifier.create(metricsFlux.take(2))
                .expectNextMatches(metr->metr.getMostPopularToppings().stream().count()>0)
                .expectNextMatches(metr->metr.getTotalCountPerTopping() >= metr.getUniqueUserCountPerTopping())
                .verifyComplete();
    }
}