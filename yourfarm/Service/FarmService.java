package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.DTO.CustomerDTO;
import com.example.yourfarm.DTO.FarmDTO;
import com.example.yourfarm.DTO.FarmerDTO;
import com.example.yourfarm.Model.Customer;
import com.example.yourfarm.Model.Farm;
import com.example.yourfarm.Model.User;
import com.example.yourfarm.Repository.AuthRepository;
import com.example.yourfarm.Repository.CustomerRepository;
import com.example.yourfarm.Repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {


    private final FarmRepository farmRepository;
    private final AuthRepository authRepository;
    private final AuthService authService;


    //ADMIN
    public List<Farm> getAllFarm(){
        if (farmRepository.findAll().isEmpty())
            throw new ApiException("EmptyList");
        else return farmRepository.findAll();
    }

    //FARM
    public void register(FarmDTO farm){
        User user =new User(null,farm.getUserName(),farm.getPassword(),"FARM",farm.getName(),farm.getEmail(),farm.getPhoneNumber(),null,null,null,null,null);

        authService.register(user);
//-------------------------------------------------------
        Farm farm1= new Farm(user.getId(), farm.getRegion(), farm.getNationalAddress(), farm.getCommercialRegister(), farm.getArea(),null,null,null,user);
        farmRepository.save(farm1);
        //------------------------------------------------------------------
    }

    //FARM
    public void update(Integer farmId, FarmDTO farm) {
        User user = authRepository.findUserById(farmId);
        Farm farm1 = farmRepository.findFarmById(farmId);

        user.setName(farm.getName());
        user.setEmail(farm.getEmail());
        user.setPhoneNumber(farm.getPhoneNumber());
        user.setPassword(farm.getPassword());
        user.setUsername(farm.getUserName());
        authRepository.save(user);

        farm1.setUser(user);
        farmRepository.save(farm1);
    }

    //ADMIN
    public void deleteCustomer(Integer farmId) {
        Farm farm1 = farmRepository.findFarmById(farmId);
        if (farm1 == null) {
            throw new ApiException(" Farm not found");
        }
        farmRepository.delete(farm1);
    }
}
