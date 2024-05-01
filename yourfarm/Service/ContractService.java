package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.DTO.CustomerDTO;
import com.example.yourfarm.Model.Contract;
import com.example.yourfarm.Model.Customer;
import com.example.yourfarm.Model.User;
import com.example.yourfarm.Repository.AuthRepository;
import com.example.yourfarm.Repository.CompanyRepository;
import com.example.yourfarm.Repository.ContractRepository;
import com.example.yourfarm.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {
//
//    private final ContractRepository contractRepository;
//
//
//
//    //ADMIN
//    public List<Contract> getAllContract(){
//        if (contractRepository.findAll().isEmpty())
//            throw new ApiException("EmptyList");
//        else return contractRepository.findAll();
//    }
//
//    //CUSTOMER
//    public void register( CustomerDTO customer){
//
//
//        Customer customer1= new Customer(user.getId(),null,null,null,user);
//        customerRepository.save(customer1);
//        //------------------------------------------------------------------
//    }
//
//    //CUSTOMER
//    public void update(Integer customerId, CustomerDTO customer) {
//        User user = authRepository.findUserById(customerId);
//        Customer customer1= customerRepository.findCustomerById(customerId);
//
//        user.setName(customer.getName());
//        user.setEmail(customer.getEmail());
//        user.setPhoneNumber(customer.getPhoneNumber());
//        user.setPassword(customer.getPassword());
//        user.setUserName(customer.getUserName());
//        authRepository.save(user);
//
//        customer1.setUser(user);
//        customerRepository.save(customer1);
//    }
//
//    //ADMIN
//    public void deleteCustomer(Integer customerId) {
//        Customer customer1 = customerRepository.findCustomerById(customerId);
//        if (customer1 == null) {
//            throw new ApiException(" Customer not found");
//        }
//        customerRepository.delete(customer1);
//    }
}
