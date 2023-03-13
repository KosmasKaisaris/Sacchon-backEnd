package org.sacchonTeam3.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "PatientMeasurements")
@NoArgsConstructor
public class PatientMeasurements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "glucose")
    private double glucose;  
    @Column(name = "carb")
    private double carb;  
    @Column(name = "measurementDate")
    private LocalDate measurementDate;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
