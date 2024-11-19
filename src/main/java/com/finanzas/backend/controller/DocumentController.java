package com.finanzas.backend.controller;

import com.finanzas.backend.entities.Bank;
import com.finanzas.backend.entities.Document;
import com.finanzas.backend.mapping.DocumentMapper;
import com.finanzas.backend.resource.create.CreateDocumentResource;
import com.finanzas.backend.resource.create.UpdateDocumentResource;
import com.finanzas.backend.resource.response.DocumentResource;
import com.finanzas.backend.service.IDocumentService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/documents")
@Api(tags = "Documents", value = "Web Service RESTFul of Documents")
public class DocumentController {
    private final IDocumentService documentService;
    private final DocumentMapper documentMapper;

    public DocumentController(IDocumentService documentService, DocumentMapper documentMapper) {
        this.documentService = documentService;
        this.documentMapper = documentMapper;
    }

    @GetMapping
    public ResponseEntity<List<DocumentResource>> getDocuments() throws Exception {
        try{
            List<Document> documents = documentService.getAll();
            if(!documents.isEmpty()){
                return new ResponseEntity<>(documents.stream().map(documentMapper::toResource).collect(Collectors.toList()), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<DocumentResource>> getDocumentsByUser(@PathVariable("idUser") Long idUser) throws Exception {
        try{
            return new ResponseEntity<>(documentService.getDocumentsByUserId(idUser).stream().map( documentMapper::toResource).collect(Collectors.toList()),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/portfolio/{idPortfolio}")
    public ResponseEntity<List<DocumentResource>> getDocumentsByPortfolio(@PathVariable("idPortfolio") Long idPortfolio) throws Exception {
        try{
            return new ResponseEntity<>(documentService.getDocumentsByPortfolioId(idPortfolio).stream().map( documentMapper::toResource).collect(Collectors.toList()),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create/{idUser}")
    public ResponseEntity<DocumentResource> createDocument(@PathVariable("idUser") Long idUser, @RequestBody CreateDocumentResource documentResource) throws Exception {
       try{
           return new ResponseEntity<>(documentMapper.toResource(documentService.saveWithUser(idUser,documentMapper.toModelCreate(documentResource))), HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping("/update/{idDocument}")
    public ResponseEntity<DocumentResource> updateDocument(@PathVariable("idDocument") Long idDocument, @RequestBody UpdateDocumentResource documentResource) throws Exception {
        try{
            return new ResponseEntity<>(documentMapper.toResource(documentService.updateDocument(idDocument,documentMapper.toModelUpdate(documentResource))), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{idDocument}")
    public ResponseEntity<?> deleteDocument(@PathVariable("idDocument") Long idDocument) throws Exception {
        try{
            documentService.delete(idDocument);
            if(!documentService.getById(idDocument).isPresent())
                return new ResponseEntity<>(HttpStatus.OK);
            else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
