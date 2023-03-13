package org.sacchonTeam3.dto;

import java.time.LocalDate;
import org.sacchonTeam3.model.PatientMeasurements;
import org.sacchonTeam3.utils.MappingUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientMeasurementsDto {
    private int id;
    private double glucose;  
    private double carb;  
    private LocalDate measurementDate;
    private PatientDto patient;

    public PatientMeasurementsDto(PatientMeasurements patientMeasurements){
        if(patientMeasurements != null){
            id = patientMeasurements.getId();
            glucose = patientMeasurements.getGlucose();  
            carb = patientMeasurements.getCarb();  
            measurementDate = patientMeasurements.getMeasurementDate();
            patient = MappingUtils.patientToDto(patientMeasurements.getPatient());
        }
    }
}
