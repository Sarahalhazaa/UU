package com.example.yourfarm.Repository;

import com.example.yourfarm.Model.OrderGuidance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGuidanceRepository extends JpaRepository<OrderGuidance,Integer> {
}
