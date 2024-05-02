package com.example.yourfarm.Repository;

import com.example.yourfarm.Model.OrderPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPlantRepository extends JpaRepository<OrderPlant,Integer> {

    List<OrderPlant> findOrdersByCompanyId(Integer companyId);

    List<OrderPlant> findOrdersByCustomerId(Integer customerId);

    List<OrderPlant> findOrdersByFarmId(Integer farmId);
}
