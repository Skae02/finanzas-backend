package com.finanzas.backend.mapping;


import com.finanzas.backend.entities.Portfolio;

import com.finanzas.backend.resource.create.CreatePortfolioResource;
import com.finanzas.backend.resource.create.UpdatePortfolioResource;

import com.finanzas.backend.resource.response.PortfolioResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
@Service
public class PortfolioMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public PortfolioResource toResource(Portfolio portfolio) {
        return mapper.map(portfolio, PortfolioResource.class);
    }
    public Portfolio toModelCreate(CreatePortfolioResource resource) {
        return mapper.map(resource, Portfolio.class);
    }
    public Portfolio toModelUpdate(UpdatePortfolioResource resource) {
        return mapper.map(resource, Portfolio.class);
    }
}
