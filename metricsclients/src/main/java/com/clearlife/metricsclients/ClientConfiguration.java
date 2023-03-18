package com.clearlife.metricsclients;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    @Bean
    @Profile("sse")
    public MetricsClient webClientMetricsClient(WebClient webClient){
        return new WebClientMetricsClient(webClient);
    }

    @Bean
    @Profile("rsocket")
    public MetricsClient rSocketMetricsClient(RSocketRequester rSocketRequester){
        return new RSocketMetricsClient(rSocketRequester);
    }

    @Bean
    public RSocketRequester socketRequester(RSocketRequester.Builder builder){
        return builder.connectTcp("localhost", 7070).block();
    }


    @Bean
    @ConditionalOnMissingBean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
