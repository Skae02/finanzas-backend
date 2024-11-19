package com.finanzas.backend.resource.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreatePortfolioResource {
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate discountDate;
}
