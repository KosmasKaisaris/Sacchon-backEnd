package org.sacchonTeam3.repository;

import org.sacchonTeam3.model.PatientMeasurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;


@Repository
public interface PatientMeasurementsRepository extends JpaRepository<PatientMeasurements, Integer> {

    @Query( value = "SELECT m FROM PatientMeasurements m WHERE m.patient.id = :patientId ")
    List<PatientMeasurements> findMeasurementByPatient(int patientId);
}
