package org.sacchonTeam3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChiefDoctor extends Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;




}
