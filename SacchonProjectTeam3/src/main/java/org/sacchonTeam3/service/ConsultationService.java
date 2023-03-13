package org.sacchonTeam3.service;

import java.util.List;

import org.sacchonTeam3.dto.ConsultationDto;
import org.sacchonTeam3.exception.ConsultationNotFoundException;
import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.exception.DatabaseException;


public interface ConsultationService{

    //viewing a consultation of a patient (or maybe a list????)
    ConsultationDto findPatientConsultation(int conId) throws ConsultationNotFoundException;

    List<ConsultationDto> findDoctorConsultation(int doctorId) throws ConsultationNotFoundException;

    //creating a consultation
    boolean createConsultation(ConsultationDto consultationDto) throws CreatedException, DatabaseException;

    //updating a consultation
    boolean update_modifyConsultationOfPatient(ConsultationDto consultationDto);

}