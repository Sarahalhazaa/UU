package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.DTO.CustomerDTO;
import com.example.yourfarm.DTO.SpecialistDTO;
import com.example.yourfarm.Model.*;
import com.example.yourfarm.Repository.AuthRepository;
import com.example.yourfarm.Repository.CustomerRepository;
import com.example.yourfarm.Repository.OrderGuidanceRepository;
import com.example.yourfarm.Repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialistService {


    private final SpecialistRepository specialistRepository;
    private final AuthRepository authRepository;
    private final AuthService authService;
    private final OrderGuidanceRepository orderGuidanceRepository;


    //ALL
    public List<Specialist> getAllSpecialist(){
        if (specialistRepository.findAll().isEmpty())
            throw new ApiException("EmptyList");
        else return specialistRepository.findAll();
    }

    //SPECIALIST
    public void register(SpecialistDTO specialist){
        User user =new User(null,specialist.getUserName(),specialist.getPassword(),"SPECIALIST",specialist.getName(),specialist.getEmail(),specialist.getPhoneNumber(),null,null,null,null,null);

        authService.register(user);
//-------------------------------------------------------
        Specialist specialist1= new Specialist(user.getId(),specialist.getYearsOfExperience(),specialist.getLicenses(),null,null,user);
        specialistRepository.save(specialist1);
        //------------------------------------------------------------------
    }

    //SPECIALIST
    public void update(Integer specialistId, SpecialistDTO specialist) {
        User user = authRepository.findUserById(specialistId);
        Specialist specialist1 = specialistRepository.findSpecialistById(specialistId);

        user.setName(specialist.getName());
        user.setEmail(specialist.getEmail());
        user.setPhoneNumber(specialist.getPhoneNumber());
        user.setPassword(specialist.getPassword());
        user.setUsername(specialist.getUserName());
        authRepository.save(user);

        specialist1.setUser(user);
        specialistRepository.save(specialist1);
    }

    //ADMIN
    public void deleteSpecialist(Integer specialistId) {
        Specialist specialist1 = specialistRepository.findSpecialistById(specialistId);
        if (specialist1 == null) {
            throw new ApiException("specialist not found");
        }
        specialistRepository.delete(specialist1);
    }

    //-------------------------------- End CRUD ---------------------------------

    public List<OrderGuidance> NewOrdersToSpecialist(Integer specialistId) {
        ArrayList<OrderGuidance> orders2 = new ArrayList<>();
        List<OrderGuidance> orders3 = orderGuidanceRepository.findOrderGuidanceBySpecialistId(specialistId);

        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (OrderGuidance orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Waiting")){
                orders2.add(orders1);
            }}

        return orders2;
    }

    public List<OrderGuidance> currentOrdersToSpecialist(Integer specialistId) {
        ArrayList<OrderGuidance> orders2 = new ArrayList<>();

        List<OrderGuidance> orders3 = orderGuidanceRepository.findOrderGuidanceBySpecialistId(specialistId);
        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (OrderGuidance orders1 : orders3) {
            if ( orders1.getStatus().equalsIgnoreCase("accepted")  ){
                orders2.add(orders1);
            }}

        return orders2;
    }

    public List<OrderGuidance> previousOrdersToSpecialist(Integer specialistId) {
        ArrayList<OrderGuidance> orders2 = new ArrayList<>();
        List<OrderGuidance> orders3 = orderGuidanceRepository.findOrderGuidanceBySpecialistId(specialistId);

        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (OrderGuidance orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Guided")|| orders1.getStatus().equalsIgnoreCase("Rejected")){
                orders2.add(orders1);
            }}

        return orders2;
    }
}
