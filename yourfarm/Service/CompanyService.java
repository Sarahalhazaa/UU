package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.DTO.CompanyDTO;
import com.example.yourfarm.DTO.CustomerDTO;
import com.example.yourfarm.Model.*;
import com.example.yourfarm.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final AuthRepository authRepository;
    private final AuthService authService;
    private final OrderPlantRepository orderPlantRepository;
    private final OrderGuidanceRepository orderGuidanceRepository;
    private final OrderFarmerRepository orderFarmerRepository;
    private final ContractRepository contractRepository;


    //ADMIN
    public List<Company> getAllCompany(){
        if (companyRepository.findAll().isEmpty())
            throw new ApiException("EmptyList");
        else return companyRepository.findAll();
    }

    //COMPANY
    public void register(CompanyDTO company) {
        User user =new User(null,company.getUserName(),company.getPassword(),"COMPANY",company.getName(),company.getEmail(),company.getPhoneNumber(),null,null,null,null,null);
        authService.register(user);

        Company company1= new Company(user.getId(), company.getRegion(), company.getNationalAddress(), company.getCommercialRegister(),null,null,null,null,user);
        companyRepository.save(company1);

    }

    //COMPANY
    public void update(Integer companyId, CompanyDTO company) {
        User user = authRepository.findUserById(companyId);
        Company company1 = companyRepository.findCompanyById(companyId);

        user.setName(company.getName());
        user.setEmail(company.getEmail());
        user.setPhoneNumber(company.getPhoneNumber());
        user.setPassword(company.getPassword());
        user.setUsername(company.getUserName());
        authRepository.save(user);

        company1.setUser(user);
        companyRepository.save(company1);
    }

    //ADMIN
    public void deleteCompany(Integer companyId) {
        Company company1 = companyRepository.findCompanyById(companyId);

        if (company1 == null) {
            throw new ApiException(" Company not found");
        }
        companyRepository.delete(company1);
    }

    //-------------------------------------   end CRUD  ---------------------------

    public List<OrderPlant> currentPlantOrders(Integer companyId) {
        ArrayList<OrderPlant> orders2 = new ArrayList<>();

        List<OrderPlant> orders3 =orderPlantRepository.findOrdersByCompanyId(companyId);
        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (OrderPlant orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Ready to deliver") || orders1.getStatus().equalsIgnoreCase("waiting") || orders1.getStatus().equalsIgnoreCase("accepted")  ){
                orders2.add(orders1);
            }}

        return orders2;
    }


    public List<OrderPlant> previousPlantOrders(Integer companyId) {
        ArrayList<OrderPlant> orders2 = new ArrayList<>();
        List<OrderPlant> orders3 = orderPlantRepository.findOrdersByCompanyId(companyId);

        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (OrderPlant orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Delivered")|| orders1.getStatus().equalsIgnoreCase("Rejected")){
                orders2.add(orders1);
            }}

        return orders2;
    }

    public List<OrderFarmer> FarmerOrders(Integer companyId) {
        ArrayList<OrderFarmer> orders2 = new ArrayList<>();
        List<OrderFarmer> orders3 = orderFarmerRepository.findOrderFarmerByCompanyId(companyId);

        if (orders3== null) {
            throw new ApiException(" orders not found");
        }


        return orders2;
    }

    public List<OrderGuidance> GuidanceOrders(Integer companyId) {
        ArrayList<OrderGuidance> orders2 = new ArrayList<>();
        List<OrderGuidance> orders3 = orderGuidanceRepository.findOrderGuidanceByCompanyId(companyId);

        if (orders3== null) {
            throw new ApiException(" orders not found");
        }


        return orders2;
    }

    public List<Contract> Contract(Integer customerId) {
        ArrayList<Contract> Contract2 = new ArrayList<>();
        List<Contract> Contract3 = contractRepository.findContractByCompanyId(customerId);

        if (Contract3== null) {
            throw new ApiException(" Contract not found");
        }


        return Contract2;
    }


}
