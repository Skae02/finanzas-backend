package com.finanzas.backend.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="banks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ruc", length = 11, unique = true, nullable = false)
    private String ruc;

    @Column(name= "img", nullable = false)
    private String img;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "tea",  nullable = false)
    private float tea;

    @Column(name = "tna", nullable = false)
    private float tna;

    @Column(name = "initial_cost", nullable = false)
    private float initialCosts;

    @Column(name = "final_cost", nullable = false)
    private float finalCosts;

}
