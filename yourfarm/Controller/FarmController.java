package com.example.yourfarm.Controller;

import com.example.yourfarm.API.ApiResponse;
import com.example.yourfarm.DTO.FarmDTO;
import com.example.yourfarm.Model.Company;
import com.example.yourfarm.Model.Farm;
import com.example.yourfarm.Model.User;
import com.example.yourfarm.Service.FarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Farm")
@RequiredArgsConstructor
public class FarmController {
    private final FarmService farmService;

    Logger logger = LoggerFactory.getLogger(FarmController.class);


    @GetMapping("/farms")
    public ResponseEntity getAllFarm(){
        logger.info("get all farm");
        return ResponseEntity.ok(farmService.getAllFarm());
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid FarmDTO farmDTO){
        farmService.register(farmDTO);
        logger.info("farm registered");
        return ResponseEntity.ok(new ApiResponse("farm registered"));
    }


    @PutMapping("/update")
    public ResponseEntity updateFarm(@AuthenticationPrincipal User user, @RequestBody @Valid FarmDTO farmDTO){
        farmService.update(user.getFarm().getId(), farmDTO);
        logger.info("farm updated");
        return ResponseEntity.ok(new ApiResponse("farm updated"));
    }

    @DeleteMapping("/delete/{farm_id}")
    public ResponseEntity deleteFarm(@AuthenticationPrincipal User user, @PathVariable Integer farm_id){
        farmService.deleteFarm(farm_id);
        logger.info("farm deleted");
        return ResponseEntity.ok(new ApiResponse("farm deleted"));
    }
//-------------------------------------   end CRUD  ---------------------------

//COMPANY
@GetMapping("/NewPlantOrders")
public ResponseEntity NewPlantOrders(@AuthenticationPrincipal Farm farm){
    logger.info("get all New Plant Orders");
    return ResponseEntity.ok(farmService.NewPlantOrders(farm.getId()));
}

    //COMPANY
    @GetMapping("/currentPlantOrders")
    public ResponseEntity currentPlantOrders(@AuthenticationPrincipal Farm farm){
        logger.info("get all current Plant Orders");
        return ResponseEntity.ok(farmService.currentPlantOrders(farm.getId()));
    }
    //COMPANY
    @GetMapping("/previousPlantOrders")
    public ResponseEntity previousPlantOrders(@AuthenticationPrincipal Farm farm){
        logger.info("get all previous Plant Orders");
        return ResponseEntity.ok(farmService.previousPlantOrders(farm.getId()));
    }



    //COMPANY
    @GetMapping("/Contract")
    public ResponseEntity Contract(@AuthenticationPrincipal Farm farm){
        logger.info("get all Contract");
        return ResponseEntity.ok(farmService.previousContract());
    }
}
