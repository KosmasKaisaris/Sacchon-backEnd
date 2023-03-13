package org.sacchonTeam3.service;

import org.sacchonTeam3.dto.DoctorDto;
import org.sacchonTeam3.dto.PatientDto;
import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.exception.DoctorNotFoundException;
import org.sacchonTeam3.exception.PersonNotFoundException;

import java.util.List;


public interface DoctorService{

    DoctorDto findDoctor(int id) throws DoctorNotFoundException;

    boolean createDoctor(DoctorDto doctorDto) throws CreatedException;

    boolean removeDoctor(int id) throws PersonNotFoundException;

    //view all patients without consultation for at least one month.
    List<PatientDto> findAllPatientsWithoutConsultationInTheLastMonth();

    List<PatientDto> DoctorsPatients(int doctorId);
}
