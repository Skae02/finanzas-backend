package com.finanzas.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nominal_value", nullable = false)
    private float nominalValue;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "sale_value")
    private float saleValue;

    @Column(name = "emission_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate emissionDate;

    @Column(name = "expiration_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate expirationDate;

    @Column(name = "flow")
    private float flow;

    @Column(name = "term")
    private int term;//plazo(360 o 365)

    @Column(name = "igv")
    private boolean igv;

    @Column(name = "net_value")
    private float netValue;

    @Column(name = "receive_value")
    private float receivedValue;

    @Column(name="buyer")
    private String buyer;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", referencedColumnName = "id")
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
}
