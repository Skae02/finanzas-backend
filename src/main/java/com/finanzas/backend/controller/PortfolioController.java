package com.finanzas.backend.controller;


import com.finanzas.backend.entities.Portfolio;
import com.finanzas.backend.mapping.PortfolioMapper;
import com.finanzas.backend.resource.create.CreatePortfolioResource;
import com.finanzas.backend.resource.create.UpdatePortfolioResource;
import com.finanzas.backend.resource.response.PortfolioResource;
import com.finanzas.backend.service.IPortfolioService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/portfolios")
@Api(tags = "Documents", value = "Web Service RESTFul of Documents")
public class PortfolioController {
    private final IPortfolioService portfolioService;
    private final PortfolioMapper portfolioMapper;


    public PortfolioController(IPortfolioService portfolioService, PortfolioMapper portfolioMapper) {
        this.portfolioService = portfolioService;
        this.portfolioMapper = portfolioMapper;
    }

    @GetMapping
    public ResponseEntity<List<PortfolioResource>> findAll() throws Exception {
        try{
            return new ResponseEntity<>(portfolioService.getAll().stream().map(portfolioMapper::toResource).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<PortfolioResource> findById(@PathVariable("id") Long id) throws Exception {
        try{
            Optional<Portfolio> portfolioOptional = portfolioService.getById(id);
            if(portfolioOptional.isPresent()) {
                return new ResponseEntity<>(portfolioMapper.toResource(portfolioOptional.get()), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }  catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PortfolioResource>> findAllByUserId(@PathVariable("userId") Long userId) throws Exception {
        try{
            return new ResponseEntity<>(portfolioService.getPortfoliosByUserId(userId).stream().map(portfolioMapper::toResource).collect(Collectors.toList()), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<PortfolioResource> create(@PathVariable("userId") Long userId,@RequestBody CreatePortfolioResource portfolioResource) throws Exception {
        try{
            return new ResponseEntity<>(portfolioMapper.toResource(portfolioService.saveWithUser(userId, portfolioMapper.toModelCreate(portfolioResource))), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PortfolioResource> update(@PathVariable("id") Long id, @RequestBody UpdatePortfolioResource portfolioResource) throws Exception {
        try{
            return new ResponseEntity<>(portfolioMapper.toResource(portfolioService.updatePortfolio(id,portfolioMapper.toModelUpdate(portfolioResource))), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePortfolio(@PathVariable("id") Long id) throws Exception {
        try{
            portfolioService.delete(id);
            if(!portfolioService.getById(id).isPresent())
                return new ResponseEntity<>(HttpStatus.OK);
            else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }









}
