package org.sacchonTeam3.service;

import lombok.AllArgsConstructor;

import org.sacchonTeam3.dto.PatientMeasurementsDto;
import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.exception.MeasurementsException;
import org.sacchonTeam3.exception.PersonNotFoundException;
import org.sacchonTeam3.model.PatientMeasurements;
import org.sacchonTeam3.repository.PatientMeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.sacchonTeam3.utils.MappingUtils;
import org.sacchonTeam3.validations.Validator;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientMeasurementsServiceImpl implements PatientMeasurementsService{
    @Autowired
    private final PatientMeasurementsRepository patientMeasurementsRepository;
    
    @Override
    public PatientMeasurementsDto storePatientMeasurement(PatientMeasurementsDto patientMeasurementsDto) throws CreatedException {
        Validator validator = new Validator();
        if(validator.measurementValidation(patientMeasurementsDto)){
            PatientMeasurements measures = MappingUtils.DtoToPatientMeasurements(patientMeasurementsDto);
            return MappingUtils.PatientMeasurementsToDto(patientMeasurementsRepository.save(measures));
        }
        else
        {throw new CreatedException("Invalid patient Measurements");}
    }

    @Override
    public List<PatientMeasurementsDto> findPatientMeasurement(int patientId) {
        return patientMeasurementsRepository
        .findMeasurementByPatient(patientId) 
        .stream()
        .map(m -> MappingUtils.PatientMeasurementsToDto(m))
        .collect(Collectors.toList());       
    }

    @Override
    public boolean update_modifyPatientMeasurement(PatientMeasurementsDto patientMeasurementsDto) throws CreatedException {
        Validator validator = new Validator();
        if(validator.measurementValidation(patientMeasurementsDto)) {
            Optional<PatientMeasurements> measurement = patientMeasurementsRepository.findById(patientMeasurementsDto.getId());
            if(measurement.isPresent()){
                patientMeasurementsRepository.save(MappingUtils.DtoToPatientMeasurements(patientMeasurementsDto));
                return true;
            }
            return false;
        }
        else
       {throw new CreatedException("Cant update because we have Invalid patient Measurements");}
    }

    @Override
    public boolean removePatientMeasurement(int msrId) throws PersonNotFoundException, MeasurementsException {
        try {
            patientMeasurementsRepository.deleteById(msrId);
        } catch (Exception e) {
            throw new MeasurementsException("Can not find Patient Measurement");
        }
        return true;
    }

    @Override
    public PatientMeasurementsDto averageMeasurements(Integer patientId) {
        List<PatientMeasurementsDto> listMeasurements = findPatientMeasurement(patientId);
        double numberOfglucose = 0;
        double numberOfCarbs = 0;
        double glucose = 0;
        double carbs = 0;
        for(PatientMeasurementsDto measures: listMeasurements){
            if(measures.getGlucose() > 0){
                glucose += measures.getGlucose();
                numberOfglucose ++;
            }
            if(measures.getCarb() > 0){
                carbs += measures.getCarb();
                numberOfCarbs ++;
            }
        }
        if(numberOfglucose > 0){
             glucose = glucose/ numberOfglucose;
        }
        if(numberOfCarbs > 0){
            carbs = carbs / numberOfCarbs;
        }
        PatientMeasurementsDto averageMeasure = new PatientMeasurementsDto();
        averageMeasure.setCarb(carbs);
        averageMeasure.setGlucose(glucose);
        return averageMeasure;
    }
}