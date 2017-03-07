package com.riojano0.recipe.domain;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PREPARATIONS")
public class Preparation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIME_PREPARATION")
    private int timePreparation;

    @Column(name = "TIME_COOKING")
    private int timeCooking;

    @Column(name = "READY_IN")
    private int readyIn;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Step> steps = new ArrayList<>();

}
