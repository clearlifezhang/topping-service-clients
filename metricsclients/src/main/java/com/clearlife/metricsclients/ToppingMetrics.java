package com.clearlife.metricsclients;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToppingMetrics {
    private Integer totalCountPerTopping;
    private Integer uniqueCountPerTopping;
    private List<String> mostPopularToppings;
    private List<String> leastPopularToppings;
}