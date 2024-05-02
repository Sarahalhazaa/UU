package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.Model.Farmer;
import com.example.yourfarm.Model.Guidance;
import com.example.yourfarm.Model.OrderFarmer;
import com.example.yourfarm.Model.Specialist;
import com.example.yourfarm.Repository.FarmerRepository;
import com.example.yourfarm.Repository.GuidanceRepository;
import com.example.yourfarm.Repository.OrderFarmerRepository;
import com.example.yourfarm.Repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFarmerService {

    private final OrderFarmerRepository orderFarmerRepository;
    private final FarmerRepository farmerRepository;

    //ADMIN
    public List<OrderFarmer> getAllOrderFarmer(){
        if (orderFarmerRepository.findAll().isEmpty())
            throw new ApiException("EmptyList");
        else return orderFarmerRepository.findAll();
    }

    //COMPANY - CUSTOMER " يحتاج لها تعديل
//    public void addOrderFarmer( Integer FarmerId , Guidance guidance){
//        Specialist specialist1 = specialistRepository.findSpecialistById(specialistId);
//        guidance.setSpecialist(specialist1);
//        guidanceRepository.save(guidance);
//
//    }


}
