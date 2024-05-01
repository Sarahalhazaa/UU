package com.example.yourfarm.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Contract Details must not be empty")
    @Column(columnDefinition = "varchar(200) not null ")
    private String ContractDetails;

    @Pattern(regexp ="^(valid contract|expired contract|waiting|acceptable)$")
    private String status;

    @Pattern(regexp = "YYYY/MM/DD")
    private LocalDate ContractStartingDate;

    @Pattern(regexp = "YYYY/MM/DD")
    private LocalDate ContractEndDate;

    //--------------------------------


    @ManyToOne
    @JsonIgnore
    private Company company;

    @ManyToOne
    @JsonIgnore
    private Farm farm;



}
