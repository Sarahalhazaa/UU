package com.example.yourfarm.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Farmer {
    @Id
    private Integer id;

    @NotNull(message ="Years Of Experience must not be empty" )
    @Column(columnDefinition = "int not null")
    private Integer YearsOfExperience;

    @NotEmpty(message = "Licenses must not be empty")
    @Column(columnDefinition = "varchar(100) not null ")
    private String Licenses;
    //------------------------------------------

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farmer")
    private Set<OrderFarmer> orderFarmers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farmer")
    private Set<serviceFarmer> serviceFarmers;

    @OneToOne
    @MapsId
    private User user;
}
