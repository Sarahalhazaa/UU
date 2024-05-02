package com.example.yourfarm.Repository;

import com.example.yourfarm.Model.Guidance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuidanceRepository extends JpaRepository<Guidance,Integer> {
    Guidance findGuidanceById(Integer id);
}
