package com.finanzas.backend.mapping;

import com.finanzas.backend.entities.Document;
import com.finanzas.backend.resource.create.CreateDocumentResource;
import com.finanzas.backend.resource.create.UpdateDocumentResource;
import com.finanzas.backend.resource.create.UpdateUserResource;
import com.finanzas.backend.resource.response.DocumentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class DocumentMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public DocumentResource toResource(Document document) {
        return mapper.map(document, DocumentResource.class);
    }
    public Document toModelCreate(CreateDocumentResource resource) {
        return mapper.map(resource, Document.class);
    }
    public Document toModelUpdate(UpdateDocumentResource resource) {
        return mapper.map(resource, Document.class);
    }


}
