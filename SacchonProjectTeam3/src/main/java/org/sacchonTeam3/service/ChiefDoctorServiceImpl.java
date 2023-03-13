package org.sacchonTeam3.service;

import lombok.AllArgsConstructor;
import org.sacchonTeam3.dto.ConsultationDto;
import org.sacchonTeam3.dto.DoctorDto;
import org.sacchonTeam3.dto.PatientDto;
import org.sacchonTeam3.dto.PatientMeasurementsDto;
import org.sacchonTeam3.model.Consultation;
import org.sacchonTeam3.model.Doctor;
import org.sacchonTeam3.model.Patient;
import org.sacchonTeam3.model.PatientMeasurements;
import org.sacchonTeam3.repository.ConsultationRepository;
import org.sacchonTeam3.repository.PatientMeasurementsRepository;
import org.sacchonTeam3.utils.MappingUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Service
@AllArgsConstructor
public class ChiefDoctorServiceImpl implements ChiefDoctorService{

    private final PatientMeasurementsRepository patientMeasurementsRepository;

    private final ConsultationRepository consultationRepository;


    @Override
    public List<PatientMeasurementsDto> readPatientMeasurements(int id, LocalDate period) {
        List<PatientMeasurements> listMeasurements = patientMeasurementsRepository.findMeasurementByPatient(id);
        List<PatientMeasurementsDto> patientMeasurementsDtoList = new ArrayList<>();
        for(PatientMeasurements measures: listMeasurements){
            if(measures.getMeasurementDate().isAfter(period)){
                patientMeasurementsDtoList.add(MappingUtils.PatientMeasurementsToDto(measures));
            }
        }
        return patientMeasurementsDtoList;
    }

    @Override
    public List<ConsultationDto> readDoctorConsultations(int id, LocalDate period) {
        List<Consultation> listOfConsultations = consultationRepository.findConsultationByDoctor(id);
        List<ConsultationDto> listOfConsultationsDto = new ArrayList<>();
        for(Consultation consultation: listOfConsultations){
            listOfConsultationsDto.add(MappingUtils.ConsultationToDto(consultation));
        }
        return listOfConsultationsDto;
    }

    @Override
    public List<Object[]> showNumberOfConsultationsPerPatient(LocalDate dateFrom, LocalDate dateTo) {
        return consultationRepository.numberOfConsultationsPerPatient(dateFrom, dateTo);
    }

    @Override
    public List<PatientDto> findInactivePatients(LocalDate dateFrom, LocalDate dateTo) {
        List<Patient> patientList = new ArrayList<>();
        patientList =  patientMeasurementsRepository.findAll()
                                                    .stream()
                                                    .filter(c -> !c.getMeasurementDate().isBefore(dateTo) && !c.getMeasurementDate().isAfter(dateFrom))                  
                                                    .map( b -> b.getPatient())
                                                    .distinct()
                                                    .collect(Collectors.toList());

        return patientList.stream()
        .map(PatientDto::new)
        .collect(Collectors.toList());

    }

    @Override
    public List<DoctorDto> findInactiveDoctors(LocalDate dateFrom, LocalDate dateTo) {
        List<Doctor> doctorList = new ArrayList<>();
        doctorList = consultationRepository.findAll()
                                .stream()
                                .filter(c -> !c.getConsultationDate().isBefore(dateTo) && !c.getConsultationDate().isAfter(dateFrom))                  
                                .map( b -> b.getDoctor())
                                .distinct()
                                .collect(Collectors.toList());

        return doctorList.stream()
        .map(DoctorDto::new)
        .collect(Collectors.toList());
    }
}