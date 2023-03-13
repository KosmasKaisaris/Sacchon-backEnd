package org.sacchonTeam3.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sacchonTeam3.dto.ConsultationDto;
import org.sacchonTeam3.dto.DoctorDto;
import org.sacchonTeam3.dto.PatientDto;
import org.sacchonTeam3.dto.PatientMeasurementsDto;
import org.sacchonTeam3.exception.ConsultationNotFoundException;
import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.exception.DatabaseException;
import org.sacchonTeam3.exception.DoctorNotFoundException;
import org.sacchonTeam3.exception.PersonNotFoundException;
import org.sacchonTeam3.service.ConsultationService;
import org.sacchonTeam3.service.DoctorService;
import org.sacchonTeam3.service.PatientMeasurementsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
public class DoctorController {

    private DoctorService doctorServiceService;
    private ConsultationService consultationService;
    private PatientMeasurementsService patientMeasurementsService;


    //Viewing a doctor's account
    @GetMapping("/Doctor/{id}")
    public DoctorDto getDoctorDto(@PathVariable int id) throws DoctorNotFoundException{
        return doctorServiceService.findDoctor(id);
    }

    //Creating a new doctor
    @PostMapping("/DoctorCreate/{doctorDto}")
    public boolean createDoctorDto(@RequestBody DoctorDto doctorDto) throws CreatedException {
         return doctorServiceService.createDoctor(doctorDto);

    }

    //Deleting a doctor
    @DeleteMapping("/DoctorDelete/{id}")
    public boolean deleteDoctorDto(@PathVariable int id) throws PersonNotFoundException {
        return doctorServiceService.removeDoctor(id);
    }

    //Viewing all the measurements of a patient
    @GetMapping("/Measurements/{patientId}")
    public List<PatientMeasurementsDto> getPatientMeasurements(@PathVariable int patientId){
        return patientMeasurementsService.findPatientMeasurement(patientId);
    }

    //Viewing all the consultations of a patient
    @GetMapping("/Consultations/{id}")
    public List<ConsultationDto> getConsultations(@PathVariable int id) throws ConsultationNotFoundException{
        return consultationService.findDoctorConsultation(id);
    }


    //Viewing all the patients that have not had a consultation for at least a month
    @GetMapping("/PatientsWithoutConsultations")
    public List<PatientDto> getPatientsWithoutConsultations(){
        return doctorServiceService.findAllPatientsWithoutConsultationInTheLastMonth();
    }

    //Creating a new consultation
    @PostMapping("/ConsultationCreate")
    public boolean createConsultationDto(@RequestBody ConsultationDto consultationDtoDto) throws CreatedException, DatabaseException {
        return consultationService.createConsultation(consultationDtoDto);
    }


    //Updating a consultation
    @PutMapping("/UpdateConsultation")
    public boolean updateConsultation(@RequestBody ConsultationDto consultationDtoDto){
        return consultationService.update_modifyConsultationOfPatient(consultationDtoDto);
    }


    //Viewing all the patients that have not had a consultation for at least a month
    @GetMapping("/MyPatients/{id}")
    public List<PatientDto> getMyPatients(@PathVariable int id){
        return doctorServiceService.DoctorsPatients(id);
    }

}
