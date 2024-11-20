package com.finanzas.backend.service;

import com.finanzas.backend.entities.User;

import java.util.List;

public interface IUserService extends CrudService<User> {
    List<User> findByCompanyName(String companyName) throws Exception;
    User findByEmail(String email) throws Exception;
    User updateUser(Long userId,User user) throws Exception;
}
