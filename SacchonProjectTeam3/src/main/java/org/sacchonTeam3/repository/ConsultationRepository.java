package org.sacchonTeam3.repository;

import java.time.LocalDate;
import java.util.List;

import org.sacchonTeam3.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    @Query( value = "SELECT c FROM Consultation c WHERE c.patient.id = :patientId ORDER BY c.consultationDate DESC")
    Consultation findConsultationByPatient(int patientId);


    @Query( value = "SELECT c FROM Consultation c WHERE c.doctor.id = :doctorId")
    List<Consultation> findConsultationByDoctor(int doctorId);

    @Query( value = "SELECT c.patient.id, COUNT(c) FROM Consultation c WHERE c.consultationDate BETWEEN :dateFrom AND :dateTo GROUP BY c.patient.id")
    List<Object[]> numberOfConsultationsPerPatient( LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
}
