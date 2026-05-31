package com.restaurant.rewards.application.in;

import com.restaurant.rewards.domain.Reward;
import java.util.List;

public interface RewardUseCase {
    List<Reward> getAllRewards();
    List<Reward> getRewardsByCustomerId(String customerId);
}
