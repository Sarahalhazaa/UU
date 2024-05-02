package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.DTO.CustomerDTO;
import com.example.yourfarm.DTO.FarmDTO;
import com.example.yourfarm.DTO.FarmerDTO;
import com.example.yourfarm.Model.*;
import com.example.yourfarm.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {


    private final FarmRepository farmRepository;
    private final AuthRepository authRepository;
    private final AuthService authService;
    private final OrderPlantRepository orderPlantRepository;
    private final ContractRepository contractRepository;


    //ALL
    public List<Farm> getAllFarm(){
        if (farmRepository.findAll().isEmpty())
            throw new ApiException("EmptyList");
        else return farmRepository.findAll();
    }

    //FARM
    public void register(FarmDTO farm){
        User user =new User(null,farm.getUserName(),farm.getPassword(),"FARM",farm.getName(),farm.getEmail(),farm.getPhoneNumber(),null,null,null,null,null);

        authService.register(user);

        Farm farm1= new Farm(user.getId(),farm.getName(), farm.getRegion(), farm.getNationalAddress(), farm.getCommercialRegister(), farm.getArea(),null,null,null,user);
        farmRepository.save(farm1);

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

    //-------------------------------- End CRUD ---------------------------------

    public List<OrderPlant> NewPlantOrders(Integer farmId) {
        ArrayList<OrderPlant> orders2 = new ArrayList<>();
        List<OrderPlant> orders3 = orderPlantRepository.findOrdersByFarmId(farmId);

        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (OrderPlant orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Waiting")){
                orders2.add(orders1);
            }}

        return orders2;
    }

    public List<OrderPlant> currentPlantOrders(Integer farmId) {
        ArrayList<OrderPlant> orders2 = new ArrayList<>();

        List<OrderPlant> orders3 =orderPlantRepository.findOrdersByFarmId(farmId);
        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (OrderPlant orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Ready to deliver") ||  orders1.getStatus().equalsIgnoreCase("accepted")  ){
                orders2.add(orders1);
            }}

        return orders2;
    }

    public List<OrderPlant> previousPlantOrders(Integer farmId) {
        ArrayList<OrderPlant> orders2 = new ArrayList<>();
        List<OrderPlant> orders3 = orderPlantRepository.findOrdersByFarmId(farmId);

        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (OrderPlant orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Delivered")|| orders1.getStatus().equalsIgnoreCase("Rejected")){
                orders2.add(orders1);
            }}

        return orders2;
    }

    public List<Contract> NewContract(Integer farmId) {
        ArrayList<Contract> Contract2 = new ArrayList<>();
        List<Contract> Contract3 = contractRepository.findContractByFarmId(farmId);

        if (Contract3== null) {
            throw new ApiException(" orders not found");
        }
        for (Contract Contract1 : Contract3) {
            if (Contract1.getStatus().equalsIgnoreCase("Waiting")){
                Contract2.add(Contract1);
            }}

        return Contract2;
    }

    public List<Contract> currentContract(Integer farmId) {
        ArrayList<Contract> Contract2 = new ArrayList<>();
        List<Contract> Contract3 = contractRepository.findContractByFarmId(farmId);

        if (Contract3== null) {
            throw new ApiException(" orders not found");
        }
        for (Contract Contract1 : Contract3) {
            if (Contract1.getStatus().equalsIgnoreCase("valid contract") ||  Contract1.getStatus().equalsIgnoreCase("accepted")  ){
                Contract2.add(Contract1);
            }}

        return Contract2;
    }

    public List<Contract> previousContract(Integer farmId) {
        ArrayList<Contract> Contract2 = new ArrayList<>();
        List<Contract> Contract3 = contractRepository.findContractByFarmId(farmId);

        if (Contract3== null) {
            throw new ApiException(" orders not found");
        }
        for (Contract Contract1 : Contract3) {
            if (Contract1.getStatus().equalsIgnoreCase("expired contract")){
                Contract2.add(Contract1);
            }}

        return Contract2;
    }
}
