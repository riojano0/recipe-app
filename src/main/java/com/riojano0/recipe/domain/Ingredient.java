package com.riojano0.recipe.domain;


import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INGREDIENTS")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PORTION")
    private int portion;

    @Enumerated(EnumType.STRING)
    private PortionUnit portionUnit;

// Bidireccional
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID_PREPARATION")
//    private Recipe recipe;

}
