package com.restaurant.rewards.application.out;

import com.restaurant.rewards.domain.Reward;
import java.math.BigDecimal;
import java.util.List;

public interface RewardRepositoryPort {
    Reward save(Reward reward);
    List<Reward> findAll();
    List<Reward> findByCustomerId(String customerId);
    long count();
    long sumPoints();
    BigDecimal sumCashback();
}
