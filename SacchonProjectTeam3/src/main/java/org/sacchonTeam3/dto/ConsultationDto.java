package org.sacchonTeam3.dto;



import lombok.*;
import org.sacchonTeam3.model.Consultation;
import org.sacchonTeam3.utils.MappingUtils;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDto {
    private int id;
    private LocalDate consultationDate;
    private String medicationName;
    private double medicationDosage;
    private String medicalReport;
    private PatientDto patient;
    private DoctorDto doctor;



    public ConsultationDto(Consultation consultation) {
        id = consultation.getId();
        consultationDate = consultation.getConsultationDate();
        medicationName = consultation.getMedicationName();
        medicationDosage = consultation.getMedicationDosage();
        medicalReport = consultation.getMedicalReport();
        patient = MappingUtils.patientToDto(consultation.getPatient());

    }
}
