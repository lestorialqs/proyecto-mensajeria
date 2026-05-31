package com.restaurant.rewards.application.service;

import com.restaurant.rewards.application.in.RewardUseCase;
import com.restaurant.rewards.application.out.RewardRepositoryPort;
import com.restaurant.rewards.domain.Reward;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RewardService implements RewardUseCase {

    private final RewardRepositoryPort rewardRepositoryPort;

    public RewardService(RewardRepositoryPort rewardRepositoryPort) {
        this.rewardRepositoryPort = rewardRepositoryPort;
    }

    @Override
    public List<Reward> getAllRewards() {
        return rewardRepositoryPort.findAll();
    }

    @Override
    public List<Reward> getRewardsByCustomerId(String customerId) {
        return rewardRepositoryPort.findByCustomerId(customerId);
    }
}
