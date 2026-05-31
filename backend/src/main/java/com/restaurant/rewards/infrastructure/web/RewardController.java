package com.restaurant.rewards.infrastructure.web;

import com.restaurant.rewards.application.in.RewardUseCase;
import com.restaurant.rewards.domain.Reward;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardUseCase rewardUseCase;

    public RewardController(RewardUseCase rewardUseCase) {
        this.rewardUseCase = rewardUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Reward>> getAllRewards() {
        return ResponseEntity.ok(rewardUseCase.getAllRewards());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Reward>> getRewardsByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(rewardUseCase.getRewardsByCustomerId(customerId));
    }
}
