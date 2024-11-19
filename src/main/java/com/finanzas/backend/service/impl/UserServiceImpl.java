package com.finanzas.backend.service.impl;

import com.finanzas.backend.entities.User;
import com.finanzas.backend.repository.IUserRepository;
import com.finanzas.backend.service.IUserService;
import com.finanzas.backend.shared.exception.ResourceNotFoundException;
import com.finanzas.backend.shared.exception.ResourceValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public User save(User user) throws Exception {
        User user1 = userRepository.findByEmail(user.getEmail());
        if(user1 == null){
            return userRepository.save(user);
        }else{
            throw new ResourceValidationException("Already a user with the email " + user.getEmail());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findByFirstName(String firstName) throws Exception {
        return userRepository.findByFirstName(firstName);
    }

    @Override
    public User findByEmail(String email) throws Exception {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(Long userId, User user) throws Exception {
        if(userRepository.findById(userId).isPresent()){
            user.setId(userId);
            return userRepository.save(user);
        }else{
            throw new ResourceNotFoundException("User with id " + userId + " not found");
        }
    }


}
