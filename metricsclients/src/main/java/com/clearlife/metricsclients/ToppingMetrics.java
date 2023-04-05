package com.clearlife.metricsclients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToppingMetrics {
    private Integer totalCountPerTopping;
    private Integer uniqueUserCountPerTopping;
    private List<String> mostPopularToppings;
    private List<String> leastPopularToppings;
}