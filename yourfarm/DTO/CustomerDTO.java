package com.example.yourfarm.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
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

}
