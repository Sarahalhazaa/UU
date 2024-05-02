package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.DTO.CustomerDTO;
import com.example.yourfarm.Model.*;
import com.example.yourfarm.Repository.AuthRepository;
import com.example.yourfarm.Repository.CustomerRepository;
import com.example.yourfarm.Repository.GuidanceRepository;
import com.example.yourfarm.Repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuidanceService {

    private final GuidanceRepository guidanceRepository;
    private final SpecialistRepository specialistRepository;

    //All
    public List<Guidance> getAllGuidance(){
        if (guidanceRepository.findAll().isEmpty())
            throw new ApiException("EmptyList");
        else return guidanceRepository.findAll();
    }

    //Specialist
    public void addGuidance( Integer specialistId , Guidance guidance){
        Specialist specialist1 = specialistRepository.findSpecialistById(specialistId);
        guidance.setSpecialist(specialist1);
        guidanceRepository.save(guidance);

    }

    //Specialist
    public void update(Integer specialistId ,Integer guidanceId,Guidance guidance) {
        Guidance guidance1 = guidanceRepository.findGuidanceById(guidanceId);
        if (guidance1 == null) {
            throw new ApiException("Guidance not found");
        }
        if (guidance1.getSpecialist().getId() != specialistId) {
            throw new ApiException("Can not update this Guidance");
        }

        guidance1.setDuration(guidance.getDuration());
        guidance1.setType(guidance.getType());
        guidance1.setPrice(guidance.getPrice());

       guidanceRepository.save(guidance1);

    }

    //Specialist
    public void deleteGuidance(Integer specialistId , Integer guidanceId) {
        Guidance guidance1 = guidanceRepository.findGuidanceById(guidanceId);
        if (guidance1 == null) {
            throw new ApiException("Guidance not found");
        }
        if (guidance1.getSpecialist().getId() != specialistId) {
            throw new ApiException("Can not delete this Guidance");
        }
        guidanceRepository.delete(guidance1);
    }

}
