package com.example.yourfarm.Repository;

import com.example.yourfarm.Model.OrderGuidance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderGuidanceRepository extends JpaRepository<OrderGuidance,Integer> {
    List<OrderGuidance> findOrderGuidanceBySpecialistId(Integer specialistId);
    List<OrderGuidance> findOrderGuidanceByCompanyId(Integer companyId);
    List<OrderGuidance> findOrderGuidanceByCustomerId(Integer customerId);


}
