package com.finanzas.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="portfolios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "discount_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate discountDate;

    @ElementCollection
    @Column(name = "expiration_dates")
    private List<String> expirationDates;

    @ElementCollection
    @Column(name = "flow_list")
    private List<Float> flowList;

    @Column(name = "tcea")
    private float tcea;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.PERSIST)
    private List<Document> documents;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
