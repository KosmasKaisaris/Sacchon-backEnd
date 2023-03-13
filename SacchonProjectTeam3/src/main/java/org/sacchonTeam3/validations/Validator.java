package org.sacchonTeam3.validations;

import org.sacchonTeam3.dto.*;

import java.time.LocalDate;


public class Validator {

    public boolean patientValidation(PatientDto patientDto)
    {
        if(patientDto == null || (patientDto.getFirstName().length() > 100 && patientDto.getFirstName() == null)
                              || (patientDto.getLastName().length() > 100 && patientDto.getFirstName() == null)
                              || (patientDto.getId() == 0))
        {
            return false;
        }

            return true;
    }

    public boolean doctorValidation(DoctorDto doctorDto)
    {
        if(doctorDto == null || (doctorDto.getFirstName().length() > 100 && doctorDto.getFirstName() == null)
                             || (doctorDto.getLastName().length() > 100 && doctorDto.getFirstName() == null)
                             ||   (doctorDto.getId() == 0 ) )
        {
            return false;
        }

        return true;
    }

    public boolean consultationValidation(ConsultationDto consultationDto)
    {
     if(consultationDto == null || (consultationDto.getDoctor() == null)
                                || (consultationDto.getPatient() == null))
     {
         return false;
     }

        return true;
    }

    public boolean measurementValidation(PatientMeasurementsDto patientMeasurementsDto)
    {
        if(patientMeasurementsDto.getPatient() == null)
        {
            return false;
        }

        return true;
    }

    public boolean userValidation(UserDto userDto)
    {
        if(userDto.getUsername() == null && (userDto.getPassword() == null))
    {
        return false;
    }

        return true;
    }
       public boolean validConsultation(PatientDto patientDto){
           LocalDate nextMonthSameDay = patientDto.getCreationDate().plusMonths(1);
           if (nextMonthSameDay.isBefore(LocalDate.now()) || nextMonthSameDay.isEqual(LocalDate.now())) {
               return true;
           } else {
               return false;
           }
       }

       public boolean eachPatientCanHaveOneDoctor(PatientDto patientDto, ConsultationDto consultationDto){
        if( (patientDto.getId() == consultationDto.getId()) && (consultationDto.getDoctor()==null)) {
                return true;
        }
                return false;
        }


}