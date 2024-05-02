package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.DTO.CustomerDTO;
import com.example.yourfarm.DTO.FarmerDTO;
import com.example.yourfarm.Model.*;
import com.example.yourfarm.Repository.AuthRepository;
import com.example.yourfarm.Repository.CustomerRepository;
import com.example.yourfarm.Repository.FarmerRepository;
import com.example.yourfarm.Repository.OrderFarmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmerService {

    private final FarmerRepository farmerRepository;
    private final AuthRepository authRepository;
    private final AuthService authService;
    private final OrderFarmerRepository orderFarmerRepository;


    //ALL
    public List<Farmer> getAllFarmer(){
        if (farmerRepository.findAll().isEmpty())
            throw new ApiException("EmptyList");
        else return farmerRepository.findAll();
    }

    //FARMER
    public void register(FarmerDTO farmer){
        User user =new User(null,farmer.getUserName(),farmer.getPassword(),"FARMER",farmer.getName(),farmer.getEmail(),farmer.getPhoneNumber(),null,null,null,null,null);
        authService.register(user);
//-------------------------------------------------------
        Farmer farmer1= new Farmer(user.getId(),farmer.getYearsOfExperience(),farmer.getLicenses(),null,null,user);
        farmerRepository.save(farmer1);
        //------------------------------------------------------------------
    }

    //FARMER
    public void update(Integer farmerId, FarmerDTO farmer) {
        User user = authRepository.findUserById(farmerId);
        Farmer farmer1= farmerRepository.findFarmerById(farmerId);

        user.setName(farmer.getName());
        user.setEmail(farmer.getEmail());
        user.setPhoneNumber(farmer.getPhoneNumber());
        user.setPassword(farmer.getPassword());
        user.setUsername(farmer.getUserName());
        authRepository.save(user);

        farmer1.setUser(user);
        farmerRepository.save(farmer1);
    }

    //ADMIN
    public void deleteFarmer(Integer farmerId) {
        Farmer farmer1= farmerRepository.findFarmerById(farmerId);
        if (farmer1 == null) {
            throw new ApiException(" Farmer not found");
        }
        farmerRepository.delete(farmer1);
    }
    //-------------------------------- End CRUD ---------------------------------

    public List<OrderFarmer> NewOrdersToFarmer(Integer farmerId) {
        ArrayList<OrderFarmer> orders2 = new ArrayList<>();
        List<OrderFarmer> orders3 = orderFarmerRepository.findOrderFarmerByFarmerId(farmerId);

        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (OrderFarmer orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Waiting")){
                orders2.add(orders1);
            }}

        return orders2;
    }

    public List<OrderFarmer> currentOrdersToFarmer(Integer farmerId) {
        ArrayList<OrderFarmer> orders2 = new ArrayList<>();

        List<OrderFarmer> orders3 = orderFarmerRepository.findOrderFarmerByFarmerId(farmerId);
        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (OrderFarmer orders1 : orders3) {
            if ( orders1.getStatus().equalsIgnoreCase("accepted")  ){
                orders2.add(orders1);
            }}

        return orders2;
    }

    public List<OrderFarmer> previousOrdersToFarmer(Integer farmerId) {
        ArrayList<OrderFarmer> orders2 = new ArrayList<>();
        List<OrderFarmer> orders3 = orderFarmerRepository.findOrderFarmerByFarmerId(farmerId);

        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (OrderFarmer orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Planting done")|| orders1.getStatus().equalsIgnoreCase("Rejected")){
                orders2.add(orders1);
            }}

        return orders2;
    }
}
