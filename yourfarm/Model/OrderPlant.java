package com.example.yourfarm.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class OrderPlant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "YYYY/MM/DD")
    private LocalDate receivedDate;

    @Pattern(regexp = "YYYY/MM/DD")
    private LocalDate DateOfOrder;

    @Pattern(regexp = "HH-MM-SS")
    private LocalTime TimeOfReceive;

    @Pattern(regexp ="^(delivered|Ready to deliver|waiting|acceptable|rejected)$")
    private String status;

    @Positive
    @NotNull(message ="Quantity must not be empty" )
    @Column(columnDefinition = "int not null")
    private Integer Quantity=0;

    //-------------------------------------------

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JsonIgnore
    private Company company;

    @ManyToOne
    @JsonIgnore
    private Farm farm;

    @ManyToMany
    @JsonIgnore
    private Set<Plant> plants;
    ;
}
