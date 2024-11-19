package com.finanzas.backend.resource.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String ruc;

    private String phone;
    private List<PortfolioResponse> portfolios;
    private List<DocumentResponse> documents;
}
