package com.example.yourfarm.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
public class serviceFarmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message ="Duration must not be empty" )
    @Column(columnDefinition = "int not null")
    private Integer Duration;

    @NotNull(message ="price must not be empty" )
    @Column(columnDefinition = "int not null")
    private Integer price;

//---------------------------------
@OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceFarmer")
private Set<OrderFarmer> orderFarmers;

    @ManyToOne
    @JsonIgnore
    private Farmer farmer;
}
