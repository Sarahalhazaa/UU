package com.example.yourfarm.Service;

import com.example.yourfarm.API.ApiException;
import com.example.yourfarm.DTO.CustomerDTO;
import com.example.yourfarm.Model.Customer;
import com.example.yourfarm.Model.User;
import com.example.yourfarm.Repository.AuthRepository;
import com.example.yourfarm.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;
    private final AuthService authService;


    //ADMIN
    public List<Customer> getAllCustomer(){
        if (customerRepository.findAll().isEmpty())
            throw new ApiException("EmptyList");
        else return customerRepository.findAll();
    }

    //CUSTOMER
    public void register( CustomerDTO customer){
        User user =new User(null,customer.getUserName(),customer.getPassword(),"CUSTOMER",customer.getName(),customer.getEmail(),customer.getPhoneNumber(),null,null,null,null,null);

        authService.register(user);
//-------------------------------------------------------
        Customer customer1= new Customer(user.getId(),null,null,null,user);
        customerRepository.save(customer1);
        //------------------------------------------------------------------
    }

    //CUSTOMER
    public void update(Integer customerId, CustomerDTO customer) {
        User user = authRepository.findUserById(customerId);
        Customer customer1= customerRepository.findCustomerById(customerId);

        user.setName(customer.getName());
        user.setEmail(customer.getEmail());
        user.setPhoneNumber(customer.getPhoneNumber());
        user.setPassword(customer.getPassword());
        user.setUsername(customer.getUserName());
        authRepository.save(user);

        customer1.setUser(user);
        customerRepository.save(customer1);
    }

    //ADMIN
    public void deleteCustomer(Integer customerId) {
        Customer customer1 = customerRepository.findCustomerById(customerId);
        if (customer1 == null) {
            throw new ApiException(" Customer not found");
        }
        customerRepository.delete(customer1);
    }

}
