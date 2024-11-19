package com.finanzas.backend.resource.response;

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
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String ruc;
    private String phone;
    private String image;

}
