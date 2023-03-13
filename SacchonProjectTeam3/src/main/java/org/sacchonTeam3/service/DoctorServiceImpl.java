package org.sacchonTeam3.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sacchonTeam3.dto.DoctorDto;
import org.sacchonTeam3.dto.PatientDto;
import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.exception.DoctorNotFoundException;
import org.sacchonTeam3.exception.PersonNotFoundException;
import org.sacchonTeam3.model.Doctor;
import org.sacchonTeam3.model.Patient;
import org.sacchonTeam3.repository.ConsultationRepository;
import org.sacchonTeam3.repository.PatientRepository;
import org.sacchonTeam3.repository.DoctorRepository;
import org.sacchonTeam3.utils.MappingUtils;
import org.sacchonTeam3.validations.Validator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;


@Service
@AllArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private final DoctorRepository doctorRepository;
    @Autowired
    private final ConsultationRepository consultationRepository;

    @Override
    public DoctorDto findDoctor(int id) throws DoctorNotFoundException {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if(doctor.isPresent()){
            DoctorDto doctorDto = MappingUtils.doctorToDto(doctor.get()) ;
            return doctorDto;
        }
        else
        {throw new DoctorNotFoundException("Doctor isn't register");}
    }

    @Override
    public boolean createDoctor(DoctorDto doctorDto) throws CreatedException {
        Validator validator = new Validator();
        if(validator.doctorValidation(doctorDto)){
            try{
                Doctor doctor = MappingUtils.DtoToDoctor(doctorDto);
                doctorRepository.save(doctor);

            }catch (Exception e){
                throw new CreatedException("Invalid doctor fields!");
            }
        }
        return true;
    }

    @Override
    public boolean removeDoctor(int id) throws PersonNotFoundException {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if(doctor.isPresent()){
            doctor.get().setActive(false);
            doctorRepository.save(doctor.get());
            
            return true;
        }       
        return false;
    }

    @Override
    public List<PatientDto> findAllPatientsWithoutConsultationInTheLastMonth() {
        List<Patient> patientList = new ArrayList<>();

        patientList = consultationRepository.findAll().stream()
                .filter(c -> c.getConsultationDate().plusMonths(1).isBefore(LocalDate.now())
                          || c.getConsultationDate().plusMonths(1).isEqual(LocalDate.now()))
                .map( b -> b.getPatient())
                .distinct()
                .collect(Collectors.toList());

        return patientList.stream()
                .map(p -> MappingUtils.patientToDto(p))
                .collect(Collectors.toList());

    }

    @Override
    public List<PatientDto> DoctorsPatients(int doctorId) {
        List<Patient> patientList = consultationRepository.findConsultationByDoctor(doctorId)
        .stream()
        .map(c -> c.getPatient())
        .distinct()
        .collect(Collectors.toList());

        return patientList.stream()
                .map(p -> MappingUtils.patientToDto(p))
                .collect(Collectors.toList());
    }


}
