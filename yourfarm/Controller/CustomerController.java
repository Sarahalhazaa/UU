package com.example.yourfarm.Controller;

import com.example.yourfarm.API.ApiResponse;
import com.example.yourfarm.DTO.CustomerDTO;
import com.example.yourfarm.Model.Company;
import com.example.yourfarm.Model.Customer;
import com.example.yourfarm.Model.User;
import com.example.yourfarm.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
  Logger logger = LoggerFactory.getLogger(CustomerController.class);


    @GetMapping("/customers")
    public ResponseEntity getAllCustomer(){
        logger.info("get all customer");
        return ResponseEntity.ok(customerService.getAllCustomer());
    }


    @PostMapping("/register")
    public ResponseEntity registerAsCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        customerService.register(customerDTO);
        return ResponseEntity.ok(new ApiResponse("customer"));
    }


    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user, CustomerDTO customerDTO){
        customerService.update(user.getCustomer().getId(), customerDTO);
        logger.info("customer updated");
        return ResponseEntity.ok(new ApiResponse("customer update"));
    }

    @DeleteMapping("/delete/{customer_id")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user, @PathVariable Integer customer_id){
        customerService.deleteCustomer(customer_id);
        logger.info("customer deleted");
        return ResponseEntity.ok(new ApiResponse("customer deleted"));
    }
//-------------------------------------   end CRUD  ---------------------------

    //COMPANY
    @GetMapping("/currentPlantOrders")
    public ResponseEntity currentPlantOrders(@AuthenticationPrincipal Customer customer){
        logger.info("get all current Plant Orders");
        return ResponseEntity.ok(customerService.currentPlantOrders(customer.getId()));
    }
    //COMPANY
    @GetMapping("/previousPlantOrders")
    public ResponseEntity previousPlantOrders(@AuthenticationPrincipal Customer customer){
        logger.info("get all previous Plant Orders");
        return ResponseEntity.ok(customerService.previousPlantOrders(customer.getId()));
    }

    //COMPANY
    @GetMapping("/FarmerOrders")
    public ResponseEntity FarmerOrders(@AuthenticationPrincipal Customer customer){
        logger.info("get all Farmer Orders");
        return ResponseEntity.ok(customerService.FarmerOrders(customer.getId()));
    }
    //COMPANY
    @GetMapping("/GuidanceOrders")
    public ResponseEntity GuidanceOrders(@AuthenticationPrincipal Customer customer){
        logger.info("get all Guidance Orders");
        return ResponseEntity.ok(customerService.GuidanceOrders(customer.getId()));
    }
}
