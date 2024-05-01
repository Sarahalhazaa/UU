package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.DTO.CompanyDTO;
import com.example.yourfarm.DTO.CustomerDTO;
import com.example.yourfarm.Model.Company;
import com.example.yourfarm.Model.Customer;
import com.example.yourfarm.Model.User;
import com.example.yourfarm.Repository.AuthRepository;
import com.example.yourfarm.Repository.CompanyRepository;
import com.example.yourfarm.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final AuthRepository authRepository;
    private final AuthService authService;


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
//-------------------------------------------------------
        Company company1= new Company(user.getId(), company.getRegion(), company.getNationalAddress(), company.getCommercialRegister(),null,null,null,null,user);
        companyRepository.save(company1);
        //------------------------------------------------------------------
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
}
