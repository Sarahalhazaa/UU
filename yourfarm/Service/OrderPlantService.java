package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.Model.OrderGuidance;
import com.example.yourfarm.Model.OrderPlant;
import com.example.yourfarm.Repository.OrderGuidanceRepository;
import com.example.yourfarm.Repository.OrderPlantRepository;
import com.example.yourfarm.Repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderPlantService {

    private final OrderPlantRepository orderPlantRepository;


    //ADMIN
    public List<OrderPlant> getAllOrderPlant(){
        if (orderPlantRepository.findAll().isEmpty())
            throw new ApiException("EmptyList");
        else return orderPlantRepository.findAll();
    }



}
