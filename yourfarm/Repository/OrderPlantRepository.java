package com.example.yourfarm.Repository;

import com.example.yourfarm.Model.OrderPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPlantRepository extends JpaRepository<OrderPlant,Integer> {
}
