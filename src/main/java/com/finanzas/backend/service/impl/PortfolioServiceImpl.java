package com.finanzas.backend.service.impl;

import com.finanzas.backend.entities.Document;
import com.finanzas.backend.entities.Portfolio;
import com.finanzas.backend.repository.IDocumentRepository;
import com.finanzas.backend.repository.IPortfolioRepository;
import com.finanzas.backend.repository.IUserRepository;
import com.finanzas.backend.service.IPortfolioService;
import com.finanzas.backend.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PortfolioServiceImpl implements IPortfolioService {
    private final IPortfolioRepository portfolioRepository;
    private final IUserRepository userRepository;
    private final IDocumentRepository documentRepository;

    public PortfolioServiceImpl(IPortfolioRepository portfolioRepository, IUserRepository userRepository, IDocumentRepository documentRepository) {
        this.portfolioRepository = portfolioRepository;
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public List<Portfolio> getPortfoliosByUserId(Long userId) {
        if(userRepository.existsById(userId)) {
            return portfolioRepository.findPortfoliosByUserId(userId);
        }else{
            throw new ResourceNotFoundException("No user found with id " + userId);
        }
    }

    @Override
    public Portfolio updatePortfolio(Long portfolioId, Portfolio portfolio) {
        Optional<Portfolio> portfolioOptional = portfolioRepository.findById(portfolioId);
        if(portfolioOptional.isPresent()) {
            Portfolio oldPortfolio = portfolioOptional.get();
            portfolio.setId(portfolioId);
            portfolio.setUser(oldPortfolio.getUser());
            List<Document> newDocuments = portfolio.getDocuments();
            List<Document> oldDocuments = oldPortfolio.getDocuments();
            if(newDocuments == oldDocuments){
                return portfolioRepository.save(portfolio);
            }
            else if(portfolio.getDocuments() == null || portfolio.getDocuments().isEmpty()) {
                List<Document> documents = oldPortfolio.getDocuments();
                for(Document document : documents) {
                    document.setPortfolio(null);
                    documentRepository.save(document);
                }
                return portfolioRepository.save(portfolio);
            }else  {
                for(Document document : oldDocuments) {
                    document.setPortfolio(null);
                    documentRepository.save(document);
                }
                for (Document document : portfolio.getDocuments()) {
                    document.setPortfolio(portfolio);
                    documentRepository.save(document);
                }
                return portfolioRepository.save(portfolio);
            }
        }else{
            throw new ResourceNotFoundException("No portfolio found with id " + portfolioId);
        }
    }

    @Override
    public Portfolio saveWithUser(Long userId, Portfolio portfolio) {
        if(userRepository.findById(userId).isPresent()) {
            portfolio.setUser(userRepository.findById(userId).get());
            return portfolioRepository.save(portfolio);
        }else{
            throw new ResourceNotFoundException("No user found with id " + userId);
        }
    }

    @Override
    public Portfolio save(Portfolio portfolio) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {
        if(portfolioRepository.existsById(id)) {
            portfolioRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("No portfolio found with id " + id);
        }

    }

    @Override
    public List<Portfolio> getAll() throws Exception {
        return portfolioRepository.findAll();
    }

    @Override
    public Optional<Portfolio> getById(Long id) throws Exception {
        return portfolioRepository.findById(id);
    }
}
