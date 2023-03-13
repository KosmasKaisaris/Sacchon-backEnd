package org.sacchonTeam3.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;


@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class UserDto {   
    private int id;
    private String username;
    private String password;
    private PatientDto patient;
    private DoctorDto doctor;
}
