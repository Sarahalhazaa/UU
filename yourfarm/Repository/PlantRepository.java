package com.example.yourfarm.Repository;

import com.example.yourfarm.Model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant,Integer> {
}
