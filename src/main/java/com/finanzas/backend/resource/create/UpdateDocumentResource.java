package com.finanzas.backend.resource.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDocumentResource {
    private float nominalValue;
    private String type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate emissionDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate expirationDate;
    private float flow;
    private float netValue;
    private float receivedValue;
    private String buyer;
}
