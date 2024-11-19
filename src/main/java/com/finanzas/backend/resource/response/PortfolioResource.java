package com.finanzas.backend.resource.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finanzas.backend.entities.Document;
import com.finanzas.backend.entities.User;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioResource {
    private Long id;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate discountDate;

    private List<String> expirationDates;

    private List<Float> flowList;

    private float tcea;
    @OneToMany(mappedBy = "portfolio")
    private List<DocumentResponse> documents;


}
