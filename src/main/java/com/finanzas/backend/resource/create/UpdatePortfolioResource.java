package com.finanzas.backend.resource.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finanzas.backend.entities.Document;
import com.finanzas.backend.resource.response.DocumentResponse;
import lombok.*;

import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePortfolioResource {
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate discountDate;
    private List<String> expirationDates;
    private List<Float> flowList;
    private float tcea;
    private List<Document> documents;
}
