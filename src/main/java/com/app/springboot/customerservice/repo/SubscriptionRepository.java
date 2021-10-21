package com.app.springboot.customerservice.repo;

import com.app.springboot.customerservice.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    Subscription findByName(String name);
}
