package com.example.yourfarm.Repository;

import com.example.yourfarm.Model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Integer> {
    List<Contract> findContractByCompanyId(int companyId);
    List<Contract> findContractByFarmId(int farmId);
    
}
