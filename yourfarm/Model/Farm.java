package com.example.yourfarm.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Farm {
    @Id
    private Integer id;

    @NotEmpty(message = "Region must not be empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private String Region;

    @NotEmpty(message = "National address must not be empty")
    @Column(columnDefinition = "varchar(8) not null ")
    private String nationalAddress;

    @NotNull(message ="commercial Register must not be empty" )
    @Column(columnDefinition = "int not null")
    private Integer commercialRegister;

    @Positive
    @Column(columnDefinition = "int")
    private Integer area;

    //------------------------------------------

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farm")
    private Set<OrderPlant> orderPlants;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farm")
    private Set<Contract> contracts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farm")
    private Set<Plant> plants;

    @OneToOne
    @MapsId
    private User user;
}
