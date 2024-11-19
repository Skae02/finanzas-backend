package com.finanzas.backend.service.impl;

import com.finanzas.backend.entities.Document;
import com.finanzas.backend.repository.IDocumentRepository;
import com.finanzas.backend.repository.IPortfolioRepository;
import com.finanzas.backend.repository.IUserRepository;
import com.finanzas.backend.service.IDocumentService;
import com.finanzas.backend.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DocumentServiceImpl implements IDocumentService {
    private final IDocumentRepository documentRepository;
    private final IUserRepository userRepository;
    private final IPortfolioRepository portfolioRepository;

    public DocumentServiceImpl(IDocumentRepository documentRepository, IUserRepository userRepository, IPortfolioRepository portfolioRepository) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public Document saveWithUser(Long userId, Document document) {
        if(userRepository.findById(userId).isPresent()) {
            document.setUser(userRepository.findById(userId).get());
        }
        if(document.getType().equals("Letra")){
            document.setIgv(false);
            document.setTerm(365);
            document.setSaleValue(document.getNominalValue());
        }else{
            document.setIgv(true);
            document.setTerm(360);
            double saleValue = document.getSaleValue()/(1+0.18);
            document.setSaleValue((float)saleValue);
        }
        return documentRepository.save(document);
    }

    @Override
    public List<Document> getDocumentsByUserId(Long userId) {
        if(userRepository.findById(userId).isPresent()) {
            return documentRepository.findDocumentsByUserId(userId);
        }else{
            throw new ResourceNotFoundException("No user found with id " + userId);
        }
    }

    @Override
    public List<Document> getDocumentsByPortfolioId(Long portfolioId) {
        if(portfolioRepository.findById(portfolioId).isPresent()) {
            return documentRepository.findDocumentsByPortfolioId(portfolioId);
        }else{
            throw new ResourceNotFoundException("No portfolio found with id " + portfolioId);
        }
    }

    @Override
    public Document updateDocument(Long documentId, Document document) {
        Optional<Document> optional = documentRepository.findById(documentId);
        if(optional.isPresent()) {
            document.setId(documentId);
            document.setUser(optional.get().getUser());
            document.setIgv(optional.get().isIgv());
            document.setTerm(optional.get().getTerm());
            if(optional.get().getPortfolio() != null){
                document.setPortfolio(optional.get().getPortfolio());
            }
            return documentRepository.save(document);
        }else {
            throw new ResourceNotFoundException("No document found with id " + documentId);
        }

    }

    @Override
    public Document save(Document document) throws Exception {
        return null;
    }


    @Override
    public void delete(Long id) throws Exception {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Document with id " + id + " not found");
        }

    }

    @Override
    public List<Document> getAll() throws Exception {
        return documentRepository.findAll();
    }

    @Override
    public Optional<Document> getById(Long id) throws Exception {
        return documentRepository.findById(id);
    }
}
