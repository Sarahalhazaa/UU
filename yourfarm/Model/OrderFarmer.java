package com.example.yourfarm.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class OrderFarmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message ="Duration must not be empty" )
    @Column(columnDefinition = "int not null")
    private Integer Duration;

    @NotNull(message ="price must not be empty" )
    @Column(columnDefinition = "int not null")
    private Integer price;

    @Pattern(regexp = "YYYY/MM/DD")
    private LocalDate Date;

    @Pattern(regexp = "HH-MM-SS")
    private LocalTime Time;

    @Pattern(regexp ="^(Planting done|Rejected|waiting|accepted)$")
    private String status;

    //--------------------------------------------

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JsonIgnore
    private Company company;

    @ManyToOne
    @JsonIgnore
    private serviceFarmer serviceFarmer;

    @ManyToOne
    @JsonIgnore
    private Farmer farmer;


}
