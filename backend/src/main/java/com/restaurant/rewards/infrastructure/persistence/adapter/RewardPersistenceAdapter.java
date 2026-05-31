package com.restaurant.rewards.infrastructure.persistence.adapter;

import com.restaurant.rewards.application.out.RewardRepositoryPort;
import com.restaurant.rewards.domain.Reward;
import com.restaurant.rewards.infrastructure.persistence.entity.RewardEntity;
import com.restaurant.rewards.infrastructure.persistence.repository.JpaRewardRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RewardPersistenceAdapter implements RewardRepositoryPort {

    private final JpaRewardRepository repository;

    public RewardPersistenceAdapter(JpaRewardRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reward save(Reward reward) {
        RewardEntity entity = toEntity(reward);
        RewardEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<Reward> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reward> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long sumPoints() {
        Long points = repository.sumPoints();
        return points != null ? points : 0;
    }

    @Override
    public BigDecimal sumCashback() {
        BigDecimal sum = repository.sumCashback();
        return sum != null ? sum : BigDecimal.ZERO;
    }

    private RewardEntity toEntity(Reward domain) {
        RewardEntity entity = new RewardEntity();
        entity.setId(domain.getId());
        entity.setCustomerId(domain.getCustomerId());
        entity.setTransactionId(domain.getTransactionId());
        entity.setPointsEarned(domain.getPointsEarned());
        entity.setCashbackEarned(domain.getCashbackEarned());
        entity.setTimestamp(domain.getTimestamp());
        return entity;
    }

    private Reward toDomain(RewardEntity entity) {
        Reward domain = new Reward();
        domain.setId(entity.getId());
        domain.setCustomerId(entity.getCustomerId());
        domain.setTransactionId(entity.getTransactionId());
        domain.setPointsEarned(entity.getPointsEarned());
        domain.setCashbackEarned(entity.getCashbackEarned());
        domain.setTimestamp(entity.getTimestamp());
        return domain;
    }
}
