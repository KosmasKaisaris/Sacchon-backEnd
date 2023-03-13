package org.sacchonTeam3.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import org.sacchonTeam3.model.Patient;


import java.time.LocalDate;


@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class PatientDto {    

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String zipCode;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodType;
    private LocalDate creationDate;
    private LocalDate lastActive;
    private boolean isActive;
    private boolean warning;


    public PatientDto(Patient patient) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

// boolean report