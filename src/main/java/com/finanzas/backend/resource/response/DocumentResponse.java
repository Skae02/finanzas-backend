package com.finanzas.backend.resource.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finanzas.backend.entities.Portfolio;
import com.finanzas.backend.entities.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponse {
    private Long id;
    private float nominalValue;
    private String type;
    private float saleValue;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate emissionDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate expirationDate;

    private float flow;

    private int term;

    private boolean igv;

    private float netValue;

    private float receiveValue;



}
