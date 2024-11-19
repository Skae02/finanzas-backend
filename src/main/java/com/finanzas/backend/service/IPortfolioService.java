package com.finanzas.backend.service;


import com.finanzas.backend.entities.Portfolio;

import java.util.List;

public interface IPortfolioService extends CrudService<Portfolio> {
    List<Portfolio> getPortfoliosByUserId(Long userId);
    Portfolio updatePortfolio(Long portfolioId, Portfolio portfolio);
    Portfolio saveWithUser(Long userId, Portfolio portfolio);
}
