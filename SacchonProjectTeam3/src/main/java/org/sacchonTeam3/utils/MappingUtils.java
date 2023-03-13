package org.sacchonTeam3.utils;

import org.sacchonTeam3.dto.*;
import org.sacchonTeam3.model.*;

public class MappingUtils {

    /// to avoid the duplicate code we can use the library MapStruck

    ///  transfer Patient to PatientDto
    public static PatientDto patientToDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setDateOfBirth(patient.getDateOfBirth());
        patientDto.setEmail(patient.getEmail());
        patientDto.setPhoneNumber(patient.getPhoneNumber());
        patientDto.setGender(patient.getGender());
        patientDto.setBloodType(patient.getBloodType());
        patientDto.setAddress(patient.getAddress());
        patientDto.setCity(patient.getCity());
        patientDto.setZipCode(patient.getZipCode());
        patientDto.setCreationDate(patient.getCreationDate());


        return patientDto;

    }

    // transfer PatientDto to Patient
    public  static Patient DtoToPatient(PatientDto patientDto){
        Patient patient = new Patient();
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        patient.setEmail(patientDto.getEmail());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setGender(patientDto.getGender());
        patient.setBloodType(patientDto.getBloodType());
        patient.setAddress(patientDto.getAddress());
        patient.setCity(patientDto.getCity());
        patient.setZipCode(patientDto.getZipCode());
        patient.setCreationDate(patientDto.getCreationDate());

        return patient;
    }

    // transfer DoctorDto to Doctor
    public static DoctorDto doctorToDto(Doctor doctor){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setFirstName(doctor.getFirstName());
        doctorDto.setLastName(doctor.getLastName());
        doctorDto.setDateOfBirth(doctor.getDateOfBirth());
        doctorDto.setEmail(doctor.getEmail());
        doctorDto.setPhoneNumber(doctor.getPhoneNumber());
        doctorDto.setAddress(doctor.getAddress());
        doctorDto.setCity(doctor.getCity());
        doctorDto.setZipCode(doctor.getZipCode());
        doctorDto.setCreationDate(doctor.getCreationDate());

        return doctorDto;
    }

    //transfer Doctor to DoctorDto
    public static Doctor DtoToDoctor(DoctorDto doctorDto){
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setDateOfBirth(doctorDto.getDateOfBirth());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setPhoneNumber(doctorDto.getPhoneNumber());
        doctor.setAddress(doctorDto.getAddress());
        doctor.setCity(doctorDto.getCity());
        doctor.setZipCode(doctorDto.getZipCode());
        doctor.setCreationDate(doctorDto.getCreationDate());
        return doctor;
    }    
    // transfer Consultation to ConsultationDto    
    public static ConsultationDto ConsultationToDto(Consultation consultation){
        ConsultationDto consultationDto = new ConsultationDto();       
        consultationDto.setConsultationDate(consultation.getConsultationDate());
        consultationDto.setMedicationName(consultation.getMedicationName());
        consultationDto.setMedicationDosage(consultation.getMedicationDosage());
        consultationDto.setMedicalReport(consultation.getMedicalReport());
        consultationDto.setPatient(patientToDto(consultation.getPatient()));
        consultationDto.setDoctor(doctorToDto(consultation.getDoctor()));
        return consultationDto;
    }
    // transfer ConsultationDto to Consultation
    public static Consultation DtoToConsultation(ConsultationDto consultationDto){
        Consultation consultation = new Consultation();       
        consultation.setConsultationDate(consultationDto.getConsultationDate());
        consultation.setMedicationName(consultationDto.getMedicationName());
        consultation.setMedicationDosage(consultationDto.getMedicationDosage());
        consultation.setMedicalReport(consultationDto.getMedicalReport());
        consultation.setPatient(DtoToPatient(consultationDto.getPatient()));
        consultation.setDoctor(DtoToDoctor(consultationDto.getDoctor()));
        return consultation;
    }

    // transfer User to UserDto
    public static UserDto UserToDto(User user){
        UserDto userDto = new UserDto();       
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setPatient(patientToDto(user.getPatient()));
        userDto.setDoctor(doctorToDto(user.getDoctor()));
        return userDto;
    }
    // transfer UserDto to User
    public static User DtoToUser(UserDto userDto){
        User user = new User();       
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setPatient(DtoToPatient(userDto.getPatient()));
        user.setDoctor(DtoToDoctor(userDto.getDoctor()));
        return user;
    }
    // transfer PatientMeasurements to PatientMeasurementsDto
    public static PatientMeasurementsDto PatientMeasurementsToDto(PatientMeasurements patientMeasurements){
        PatientMeasurementsDto patientMeasurementsDto = new PatientMeasurementsDto();       
        patientMeasurementsDto.setId(patientMeasurements.getId());
        patientMeasurementsDto.setGlucose(patientMeasurements.getGlucose());  
        patientMeasurementsDto.setCarb(patientMeasurements.getCarb());  
        patientMeasurementsDto.setMeasurementDate(patientMeasurements.getMeasurementDate());
        patientMeasurementsDto.setPatient(patientToDto(patientMeasurements.getPatient()));
        return patientMeasurementsDto;
    }
    // transfer UserDto to User
    public static PatientMeasurements DtoToPatientMeasurements(PatientMeasurementsDto patientMeasurementsDto){
        PatientMeasurements patientMeasurements = new PatientMeasurements();       
        patientMeasurements.setId(patientMeasurementsDto.getId());
        patientMeasurements.setGlucose(patientMeasurementsDto.getGlucose());  
        patientMeasurements.setCarb(patientMeasurementsDto.getCarb());  
        patientMeasurements.setMeasurementDate(patientMeasurementsDto.getMeasurementDate());
        patientMeasurements.setPatient(DtoToPatient(patientMeasurementsDto.getPatient()));
        return patientMeasurements;
    }

}
