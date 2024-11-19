package com.finanzas.backend.mapping;

import com.finanzas.backend.entities.Portfolio;
import com.finanzas.backend.entities.User;
import com.finanzas.backend.resource.create.CreatePortfolioResource;
import com.finanzas.backend.resource.create.UpdatePortfolioResource;
import com.finanzas.backend.resource.create.UpdateUserResource;
import com.finanzas.backend.resource.response.PortfolioResource;
import com.finanzas.backend.resource.response.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

@Service
public class UserMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public UserResource toResource(User model) {
        return mapper.map(model, UserResource.class);
    }
    public User toModelUpdate(UpdateUserResource resource) {
        return mapper.map(resource, User.class);
    }
}
