package org.sacchonTeam3.service;


import org.sacchonTeam3.dto.PatientDto;

import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.exception.PersonNotFoundException;



public interface PatientService{

    PatientDto findPatient(int id) throws PersonNotFoundException;

    PatientDto createPatient(PatientDto patientDto) throws CreatedException;

    boolean removePatient(int id) throws PersonNotFoundException;


}
