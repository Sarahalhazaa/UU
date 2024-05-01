package com.example.yourfarm.Repository;

import com.example.yourfarm.Model.OrderFarmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFarmerRepository extends JpaRepository<OrderFarmer,Integer> {
}
