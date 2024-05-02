package com.example.yourfarm.Repository;

import com.example.yourfarm.Model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer,Integer> {
    Farmer findFarmerById(Integer id);
}
