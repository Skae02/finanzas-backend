package com.finanzas.backend.service;

import com.finanzas.backend.entities.Document;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

public interface IDocumentService extends CrudService<Document>{
    Document saveWithUser(Long userId, Document document);
    List<Document> getDocumentsByUserId(Long userId);
    List<Document> getDocumentsByPortfolioId(Long portfolioId);
    Document updateDocument(Long documentId, Document document);
}
