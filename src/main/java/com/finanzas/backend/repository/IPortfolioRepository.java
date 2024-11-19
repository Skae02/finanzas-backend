package com.finanzas.backend.repository;

import com.finanzas.backend.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IPortfolioRepository extends JpaRepository<Portfolio, Long> {
    Portfolio findByName(String name);
    List<Portfolio> findPortfoliosByUserId(Long userId);
}
