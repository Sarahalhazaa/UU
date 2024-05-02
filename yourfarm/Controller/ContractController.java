package com.example.yourfarm.Controller;

import com.example.yourfarm.Service.ContractService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    Logger logger = LoggerFactory.getLogger(ContractController.class);


    @GetMapping("/contract")
    public ResponseEntity getAllContract(){
        logger.info("get all contracts");
        return ResponseEntity.ok(contractService.getAllContract());
    }
    //-------------------------------------   end CRUD  ---------------------------
}
