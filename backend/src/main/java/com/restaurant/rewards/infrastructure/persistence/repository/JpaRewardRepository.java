package com.restaurant.rewards.infrastructure.persistence.repository;

import com.restaurant.rewards.infrastructure.persistence.entity.RewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface JpaRewardRepository extends JpaRepository<RewardEntity, String> {
    List<RewardEntity> findByCustomerId(String customerId);

    @Query("SELECT SUM(r.pointsEarned) FROM RewardEntity r")
    Long sumPoints();

    @Query("SELECT SUM(r.cashbackEarned) FROM RewardEntity r")
    BigDecimal sumCashback();
}
