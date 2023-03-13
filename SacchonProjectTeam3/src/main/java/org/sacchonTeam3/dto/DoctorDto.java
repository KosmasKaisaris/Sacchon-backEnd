package org.sacchonTeam3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

import org.sacchonTeam3.model.Doctor;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private int phoneNumber;
    private String address;
    private String city;
    private String zipCode;
    private LocalDate creationDate;  
    private boolean isActive;  

    public DoctorDto(Doctor doctor) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
