package com.riojano0.recipe.domain;


import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STEPS")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMBER")
    private int number;
    @Column(name = "DESCRIPTION")
    private String description;

}
