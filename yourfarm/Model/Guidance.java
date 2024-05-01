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

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Guidance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message ="Duration must not be empty" )
    @Column(columnDefinition = "int not null")
    private Integer Duration;

    @NotNull(message ="price must not be empty" )
    @Column(columnDefinition = "int not null")
    private Integer price;

    @NotEmpty(message = "type of Guidance must not be empty")
    @Column(columnDefinition = "varchar(20) not null ")
    @Pattern(regexp ="^(Presence|online)$")
    private String type;

    //--------------------------------
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guidance")
    private Set<OrderGuidance> orderGuidances;

    @ManyToOne
    @JsonIgnore
    private Specialist specialist;
}
