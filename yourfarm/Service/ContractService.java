package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.DTO.CustomerDTO;
import com.example.yourfarm.Model.Contract;
import com.example.yourfarm.Model.Customer;
import com.example.yourfarm.Model.Farm;
import com.example.yourfarm.Model.User;
import com.example.yourfarm.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final FarmRepository farmRepository;

    //ADMIN
    public List<Contract> getAllContract(){
        if (contractRepository.findAll().isEmpty())
            throw new ApiException("EmptyList");
        else return contractRepository.findAll();
    }

    //COMPANY   " يحتاج لها تعديل "
//    public void addContract( Integer farmId , Contract contract){
//        Farm farm1 = farmRepository.findFarmById(farmId);
//        contract.setFarm(farm1);
//        contractRepository.save(contract);
//
//    }
    //---------------------

}
