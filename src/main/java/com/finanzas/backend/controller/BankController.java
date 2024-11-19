package com.finanzas.backend.controller;

import com.finanzas.backend.entities.Bank;
import com.finanzas.backend.service.IBankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/banks")
@Api(tags = "Banks", value = "Web Service RESTFul of Banks")
public class BankController {
    private final IBankService bankService;

    public BankController(IBankService bankService) {
        this.bankService = bankService;
    }

    @ApiOperation(value = "List all Banks", notes = "Method to list all banks")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Banks found"),
            @ApiResponse(code = 404, message = "Banks Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bank>> getAllBanks() throws Exception {
        try{
            List<Bank> banks = bankService.getAll();
            if(!banks.isEmpty()){
                return new ResponseEntity<>(banks, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank) throws Exception {
        try {
            return new ResponseEntity<>(bankService.save(bank), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable("id") Long id) throws Exception {
        try{
            Optional<Bank> existBank = bankService.getById(id);
            if(existBank.isPresent()){
                return new ResponseEntity<>(existBank.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
