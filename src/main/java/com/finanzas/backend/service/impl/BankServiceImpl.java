package com.finanzas.backend.service.impl;

import com.finanzas.backend.entities.Bank;
import com.finanzas.backend.repository.IBankRepository;
import com.finanzas.backend.service.IBankService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements IBankService {
    private final IBankRepository bankRepository;

    public BankServiceImpl(IBankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public Bank save(Bank bank) throws Exception {
        return bankRepository.save(bank);
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Bank> bank = bankRepository.findById(id);
        if (bank.isPresent()) {
            bankRepository.delete(bank.get());
        }else {
            throw new ResourceAccessException("Bank with id " + id + " not found");
        }

    }

    @Override
    public List<Bank> getAll() throws Exception {
        return bankRepository.findAll();
    }

    @Override
    public Optional<Bank> getById(Long id) throws Exception {
        return bankRepository.findById(id);
    }
}
