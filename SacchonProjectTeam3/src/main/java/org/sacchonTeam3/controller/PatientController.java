package org.sacchonTeam3.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sacchonTeam3.dto.ConsultationDto;
import org.sacchonTeam3.dto.PatientDto;
import org.sacchonTeam3.dto.PatientMeasurementsDto;
import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.exception.MeasurementsException;
import org.sacchonTeam3.exception.PersonNotFoundException;
import org.sacchonTeam3.service.PatientMeasurementsService;
import org.sacchonTeam3.service.PatientService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
public class PatientController {

    private PatientService patientService;
    private PatientMeasurementsService measurementService;

    //Viewing a patient's account
    @GetMapping("/Patient/{id}")
    public PatientDto getPatientDto(@PathVariable int id) throws PersonNotFoundException{
        return patientService.findPatient(id);
    }

    //Creating a new patient
    @PostMapping("/PatientCreate")
    public PatientDto createPatientDto(@RequestBody PatientDto patientDto) throws CreatedException {
        return patientService.createPatient(patientDto);
    }

    //Deleting a patient
    @DeleteMapping("/PatientDelete/{id}")
    public boolean deletePatientDto(@PathVariable int id) throws PersonNotFoundException{
        return patientService.removePatient(id);
    }

    //Storing a new measurement
    @PostMapping("/StoreMeasurement")
    public PatientMeasurementsDto storePatientMeasurement(@RequestBody PatientMeasurementsDto patientMeasurement) throws CreatedException{
        return measurementService.storePatientMeasurement(patientMeasurement);
    }
   
       
    //Viewing the current consultations
    @GetMapping("/CurrentConsultations")
    public List<ConsultationDto> getCurrentConsultations(){
        return null;
    }

    @GetMapping("/PatientAverageMeasurements/{idPatient}")
    public PatientMeasurementsDto getAverageMeasurements(@PathVariable int idPatient){
        return measurementService.averageMeasurements(idPatient);
    }

    //Viewing the current consultations
    @GetMapping("/PastConsultations")
    public List<ConsultationDto> getPastConsultations(){
        return null;
    }

    //Updating incorrect submitted data
    @PostMapping("/UpdateMeasurement")
    public boolean updatePatientMeasurement(@PathVariable PatientMeasurementsDto patientMeasurement) throws CreatedException{
        return measurementService.update_modifyPatientMeasurement(patientMeasurement);
    }


    //Deleting incorrect submitted data
    @DeleteMapping("/DeleteMeasurement/{id}")
    public boolean deletePatientMeasurement(@PathVariable int id) throws PersonNotFoundException, MeasurementsException{
        return measurementService.removePatientMeasurement(id);
    }

    @GetMapping("/PatientMeasurementsDelete/{id}")
    public PatientMeasurementsDto getAverageMeasurements(@PathVariable int id){
        return measurementService.removePatientMeasurement(id);
    }

}