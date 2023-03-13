package org.sacchonTeam3.repository;

import org.sacchonTeam3.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.persistence.ConstructorResult;

import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {   
//    @Query(value = "SELECT * FROM patient P WHERE NOT EXISTS(SELECT C.patient_id FROM consultation C where P.id = C.patient_id)", nativeQuery = true)
//    List<Patient> findPatientWithNoConsultation();

}
