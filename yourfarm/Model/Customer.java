package com.example.yourfarm.Model;

import jakarta.persistence.*;
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
public class Customer {

    @Id
    private Integer id;

    //------------------------------------------
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<OrderFarmer> orderFarmers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<OrderGuidance> orderGuidances;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<OrderPlant> orderPlants;

    @OneToOne
    @MapsId
    private User user;
}
