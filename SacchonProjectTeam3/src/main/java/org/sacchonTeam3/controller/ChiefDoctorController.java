package org.sacchonTeam3.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sacchonTeam3.dto.*;
import org.sacchonTeam3.service.ChiefDoctorService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class ChiefDoctorController {

    private final ChiefDoctorService chiefDoctorService;    


    //Viewing all the measurements of a patient in a specific time period
    @GetMapping("/PatientMeasurements/{id}/{time}")
    public List<PatientMeasurementsDto> getPatientMeasurements(@PathVariable int id, @PathVariable LocalDate time){
        return chiefDoctorService.readPatientMeasurements(id, time);
    }

    //Viewing all the consultations of a doctor
    @GetMapping("/DoctorConsultations/{id}/{time}")
    public List<ConsultationDto> getDoctorConsultations(@PathVariable int id, @PathVariable LocalDate time){
        return chiefDoctorService.readDoctorConsultations(id, time);
    }   


    //Viewing all the patients and the number of consultations they got over a specific time period
    @GetMapping("/PatientsAndNumberOfConsultations/{dateFrom}/{dateTo}")
    public List<Object[]> getPatientsAndNumberOfConsultations(@PathVariable LocalDate dateFrom, @PathVariable LocalDate dateTo){
        return chiefDoctorService.showNumberOfConsultationsPerPatient(dateFrom, dateTo);
    }


    //Viewing all the inactive patients
    @GetMapping("/InactivePatients/{dateFrom}/{dateTo}")
    public List<PatientDto> getInactivePatients(@PathVariable LocalDate dateFrom, @PathVariable LocalDate dateTo){
        return chiefDoctorService.findInactivePatients(dateFrom, dateTo);
    }


    //Viewing all the inactive doctors
    @GetMapping("/InactiveDoctors/{dateFrom}/{dateTo}")
    public List<DoctorDto> getInactiveDoctors(@PathVariable LocalDate dateFrom, @PathVariable LocalDate dateTo){
        return chiefDoctorService.findInactiveDoctors(dateFrom, dateTo);
    }







}