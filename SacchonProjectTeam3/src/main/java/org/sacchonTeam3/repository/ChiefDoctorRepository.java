package org.sacchonTeam3.repository;

import org.sacchonTeam3.model.ChiefDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ChiefDoctorRepository extends JpaRepository<ChiefDoctor, Integer>{
}
