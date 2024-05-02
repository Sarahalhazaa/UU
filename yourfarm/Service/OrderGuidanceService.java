package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.Model.OrderFarmer;
import com.example.yourfarm.Model.OrderGuidance;
import com.example.yourfarm.Repository.FarmerRepository;
import com.example.yourfarm.Repository.OrderFarmerRepository;
import com.example.yourfarm.Repository.OrderGuidanceRepository;
import com.example.yourfarm.Repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderGuidanceService {

    private final OrderGuidanceRepository orderGuidanceRepository;
    private final SpecialistRepository specialistRepository;

    //ADMIN
    public List<OrderGuidance> getAllOrderGuidance(){
        if (orderGuidanceRepository.findAll().isEmpty())
            throw new ApiException("EmptyList");
        else return orderGuidanceRepository.findAll();
    }
}
