package com.example.yourfarm.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FarmerDTO {

    private Integer Id;

    @NotEmpty(message = "It must not be empty")
    @Size(min = 5,message ="You must enter at least 5 characters" )
    private String userName;

    @NotEmpty(message ="It must not be empty" )
    private String Password;

    @NotEmpty(message = "name must not be empty")
    private String name ;

    @NotEmpty(message ="It must not be empty" )
    @Email
    private String email;

    @Pattern(regexp = "05\\d{8}")
    private String phoneNumber;

    @NotNull(message ="Years Of Experience must not be empty" )
    private Integer YearsOfExperience;

    @NotEmpty(message = "Licenses must not be empty")
    private String Licenses;
}
