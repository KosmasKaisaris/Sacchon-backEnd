package org.sacchonTeam3.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.sacchonTeam3.dto.ConsultationDto;
import org.sacchonTeam3.exception.ConsultationNotFoundException;
import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.exception.DatabaseException;
import org.sacchonTeam3.model.Consultation;
import org.sacchonTeam3.repository.ConsultationRepository;
import org.sacchonTeam3.utils.MappingUtils;
import org.sacchonTeam3.validations.Validator;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;


@Service
@AllArgsConstructor
@Transactional
public class ConsultationServiceImpl implements ConsultationService{
    @Autowired
    private final ConsultationRepository consultationRepository;
    @Override
    public ConsultationDto findPatientConsultation(int patientId) throws ConsultationNotFoundException {
        Consultation consultation = consultationRepository.findConsultationByPatient(patientId);
        if(consultation != null){
            ConsultationDto consultationDto = MappingUtils.ConsultationToDto(consultation);
            return consultationDto;
        }
        else
        {throw  new ConsultationNotFoundException("Consultation with id:" + patientId + " not found");}

    }

    @Override
    public boolean createConsultation(ConsultationDto consultationDto) throws CreatedException, DatabaseException {
        Validator validator = new Validator();
        if(validator.consultationValidation(consultationDto)){
            try {
                consultationRepository.save(MappingUtils.DtoToConsultation(consultationDto));
            }catch (Exception e){
                throw new DatabaseException("Something went wrong with our database");
            }
            return true;
        }
        else
        {throw new CreatedException("Invalid consultation data");}

    }

    @Override
    public boolean update_modifyConsultationOfPatient(ConsultationDto consultationDto) {
        Validator validator = new Validator();
        if(validator.consultationValidation(consultationDto)) {
            Optional<Consultation> consultation = consultationRepository.findById(consultationDto.getId());
            if(consultation.isPresent()){
                consultationRepository.save(MappingUtils.DtoToConsultation(consultationDto));
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ConsultationDto> findDoctorConsultation(int doctorId) throws ConsultationNotFoundException {
        List<Consultation> consultationList = consultationRepository.findConsultationByDoctor(doctorId);
        List<ConsultationDto> consultationDto = new ArrayList<>();
        for(Consultation consultation : consultationList){
            consultationDto.add(MappingUtils.ConsultationToDto(consultation));
        }
        return consultationDto;
    }


}