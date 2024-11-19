package com.finanzas.backend.repository;

import com.finanzas.backend.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findDocumentsByUserId(Long userId);
    List<Document> findDocumentsByPortfolioId(Long portfolioId);
}
