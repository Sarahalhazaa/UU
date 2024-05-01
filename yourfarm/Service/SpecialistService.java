package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.DTO.CustomerDTO;
import com.example.yourfarm.DTO.SpecialistDTO;
import com.example.yourfarm.Model.Customer;
import com.example.yourfarm.Model.Specialist;
import com.example.yourfarm.Model.User;
import com.example.yourfarm.Repository.AuthRepository;
import com.example.yourfarm.Repository.CustomerRepository;
import com.example.yourfarm.Repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialistService {


    private final SpecialistRepository specialistRepository;
    private final AuthRepository authRepository;
    private final AuthService authService;


    //ADMIN
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
}
