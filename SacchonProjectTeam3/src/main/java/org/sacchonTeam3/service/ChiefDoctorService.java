package org.sacchonTeam3.service;

import org.sacchonTeam3.dto.ConsultationDto;
import org.sacchonTeam3.dto.DoctorDto;
import org.sacchonTeam3.dto.PatientDto;
import org.sacchonTeam3.dto.PatientMeasurementsDto;

import java.time.LocalDate;
import java.util.List;


public interface ChiefDoctorService {

    //Viewing all the measurements of a patient for a specific time period
    List<PatientMeasurementsDto> readPatientMeasurements(int id, LocalDate period);

    //Viewing all the consultations of a doctor for a specific time period
    List<ConsultationDto> readDoctorConsultations(int id, LocalDate period);

    //Viewing all the patients and the number of consultations the got in a specific time period
    List<Object[]> showNumberOfConsultationsPerPatient(LocalDate dateFrom, LocalDate dateTo);

    //View all the patients without activity for a specific time period
    List<PatientDto> findInactivePatients(LocalDate dateFrom, LocalDate dateTo);

    //View all the doctors without activity for a specific time period
    List<DoctorDto> findInactiveDoctors(LocalDate dateFrom, LocalDate dateTo);



}