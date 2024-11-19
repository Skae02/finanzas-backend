package com.finanzas.backend.repository;

import com.finanzas.backend.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBankRepository extends JpaRepository<Bank, Long> {

}
