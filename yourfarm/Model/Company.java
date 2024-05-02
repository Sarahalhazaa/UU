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
public class Company {
    @Id
    private Integer id;

    @NotEmpty(message = "Region must not be empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private String Region;

    @NotEmpty(message = "National address must not be empty")
    @Column(columnDefinition = "varchar(8) not null ")
    private String nationalAddress;

    @NotNull(message ="commercialRegister must not be empty" )
    @Column(columnDefinition = "int not null")
    private Integer commercialRegister;

    //------------------------------------------

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Set<OrderFarmer> orderFarmers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Set<OrderGuidance> orderGuidances;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Set<OrderPlant> orderPlants;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Set<Contract> contracts;

    @OneToOne
    @MapsId
    private User user;






//    CompanyDTO
//    CustomerDTO
//    FarmDTO
//    FarmerDTO
//    SpecialistDTO
//
//
//    GuidanceService
//    OrderFarmerService
//    OrderGuidanceService
//    OrderPlantService
//    PlantRepository
//    SpecialistDTO
//    AuthService

}
