package org.sacchonTeam3.service;



import lombok.AllArgsConstructor;
import org.sacchonTeam3.dto.PatientDto;
import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.exception.PersonNotFoundException;
import org.sacchonTeam3.model.Patient;
import org.sacchonTeam3.validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.sacchonTeam3.repository.PatientRepository;
import org.sacchonTeam3.utils.MappingUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.Optional;
@Service
@AllArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService{

    @Autowired
    private final PatientRepository patientRepository;    
    @Override
    public PatientDto findPatient(int id) throws PersonNotFoundException{
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isPresent()){
            return MappingUtils.patientToDto(patientOptional.get());
        }
        else
        {throw new PersonNotFoundException("Customer not found id= " + id);}        
    }
    @Override
    public PatientDto createPatient(PatientDto patientDto) throws CreatedException {
        Validator validator= new Validator();
        if(validator.patientValidation(patientDto)) {
            Patient patient = MappingUtils.DtoToPatient(patientDto);
            return MappingUtils.patientToDto(patientRepository.save(patient));
        }
        else
        {throw new CreatedException("Invalid fields");}
    }

    @Override
    public boolean removePatient(int id) throws PersonNotFoundException {
        try {
            patientRepository.deleteById(id);
        } catch (Exception e) {
            throw new PersonNotFoundException("Patient not found with this id:" + id);
        }
        return true;       
    }


}
