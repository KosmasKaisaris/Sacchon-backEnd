package org.sacchonTeam3.service;

import org.sacchonTeam3.dto.PatientMeasurementsDto;
import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.exception.MeasurementsException;
import org.sacchonTeam3.exception.PersonNotFoundException;

import java.util.List;


public interface PatientMeasurementsService {

    //Storing the glucose and carb measurement
    PatientMeasurementsDto storePatientMeasurement(PatientMeasurementsDto patientMeasurementsDto) throws CreatedException;

    //Finding a patient's measurement
    List<PatientMeasurementsDto> findPatientMeasurement(int patientId);

    //Updating the patient's measurement
    boolean update_modifyPatientMeasurement(PatientMeasurementsDto patientMeasurementsDto) throws CreatedException;

    //Removing a patient's measurement
    boolean removePatientMeasurement(int msrId) throws PersonNotFoundException, MeasurementsException;

    //Get Average Measurements Per Patient
    PatientMeasurementsDto averageMeasurements(Integer patientId);

}