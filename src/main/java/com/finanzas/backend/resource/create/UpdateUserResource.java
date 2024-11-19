package com.finanzas.backend.resource.create;

import com.finanzas.backend.entities.Document;
import com.finanzas.backend.entities.Portfolio;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserResource {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String ruc;

    private String phone;

    private String image;

    private List<Portfolio> portfolios;

    private List<Document> documents;
}
