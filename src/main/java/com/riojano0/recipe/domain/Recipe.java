package com.riojano0.recipe.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "RECIPES")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAMED")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PREPARATION")
    private Preparation preparation;

// Bidireccional
//    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private List<Ingredient> ingredients;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;


}
