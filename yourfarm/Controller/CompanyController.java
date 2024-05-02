package com.example.yourfarm.Controller;

import com.example.yourfarm.API.ApiResponse;
import com.example.yourfarm.DTO.CompanyDTO;
import com.example.yourfarm.Model.Company;
import com.example.yourfarm.Model.User;
import com.example.yourfarm.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    Logger logger = LoggerFactory.getLogger(CompanyController.class);

//ADMIN
    @GetMapping("/companies")
    public ResponseEntity getAllCompany(){
        logger.info("get all company");
        return ResponseEntity.ok(companyService.getAllCompany());
    }
//ALL
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CompanyDTO companyDTO){
        companyService.register(companyDTO);
        logger.info("company added");
        return ResponseEntity.ok(new ApiResponse("company created"));
    }
//COMPANY
    @PutMapping("/update")
    public ResponseEntity updateCompany(@AuthenticationPrincipal Company company, @RequestBody CompanyDTO companyDTO){
        companyService.update(company.getId(), companyDTO);
        logger.info("update company");
        return ResponseEntity.ok(new ApiResponse("company updated"));
    }
    //ADMIN
    @DeleteMapping("/delete/{company_id}")
    public ResponseEntity deleteCompany(@AuthenticationPrincipal User user, Integer company_id){
        companyService.deleteCompany(company_id);
        logger.info("company deleted");
        return ResponseEntity.ok(new ApiResponse("company deleted"));
    }
    //-------------------------------------   end CRUD  ---------------------------

    //COMPANY
    @GetMapping("/currentPlantOrders")
    public ResponseEntity currentPlantOrders(@AuthenticationPrincipal Company company){
        logger.info("get all current Plant Orders");
        return ResponseEntity.ok(companyService.currentPlantOrders(company.getId()));
    }
    //COMPANY
    @GetMapping("/previousPlantOrders")
    public ResponseEntity previousPlantOrders(@AuthenticationPrincipal Company company){
        logger.info("get all previous Plant Orders");
        return ResponseEntity.ok(companyService.previousPlantOrders(company.getId()));
    }

    //COMPANY
    @GetMapping("/FarmerOrders")
    public ResponseEntity FarmerOrders(@AuthenticationPrincipal Company company){
        logger.info("get all Farmer Orders");
        return ResponseEntity.ok(companyService.FarmerOrders(company.getId()));
    }
    //COMPANY
    @GetMapping("/GuidanceOrders")
    public ResponseEntity GuidanceOrders(@AuthenticationPrincipal Company company){
        logger.info("get all Guidance Orders");
        return ResponseEntity.ok(companyService.GuidanceOrders(company.getId()));
    }

    //COMPANY
    @GetMapping("/Contract")
    public ResponseEntity Contract(@AuthenticationPrincipal Company company){
        logger.info("get all Contract");
        return ResponseEntity.ok(companyService.Contract(company.getId()));
    }

}
