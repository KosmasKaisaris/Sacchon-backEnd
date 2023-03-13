package test.org.sacchonTeam3.service;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sacchonTeam3.dto.PatientDto;
import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.exception.PersonNotFoundException;
import org.sacchonTeam3.model.Patient;
import org.sacchonTeam3.repository.PatientRepository;
import org.sacchonTeam3.service.PatientService;
import org.sacchonTeam3.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.org.sacchonTeam3.MainTest;

import java.time.LocalDate;


@SpringBootTest(classes = MainTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PatientServiceImplTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;



    @Before
    public void beforeClass(){
        clearDB();
    }

    @After
    public void afterTest(){
        clearDB();
    }

    @Test
    public void findPatient_OK() throws PersonNotFoundException {
        Patient patient = create();
        patientRepository.save(patient);
        PatientDto patientDto = patientService.findPatient(patient.getId());

        Assert.assertNotNull(patientDto);
        Assert.assertNotNull(patientDto.getId());
        Assert.assertEquals(patientDto.getCity(),"Thessaloniki");
        Assert.assertEquals(patientDto.getFirstName(),"Anna");


    }
    @Test
    public void createPatient_OK() throws CreatedException {
        Patient patient = create();
        PatientDto patientDto = MappingUtils.patientToDto(patient);
        PatientDto createdPatiendDto =patientService.createPatient(patientDto);

        Assert.assertNotNull(createdPatiendDto);
        Assert.assertNotNull(createdPatiendDto.getId());
        Assert.assertEquals(createdPatiendDto.getAddress(),"Ierarxoy Gerbasiou");
        Assert.assertEquals(createdPatiendDto, patientRepository.findById(patient.getId()));

    }
    @Test
    public void removePatient_OK() throws PersonNotFoundException {
        Patient patient = create();
        patientRepository.save(patient);
        int id = patient.getId();
        patientRepository.deleteById(patient.getId());

        Assert.assertEquals(null ,patientService.findPatient(id));

    }



    //////////Helper Classes ////////////////

    private Patient create(){
        Patient patient = new Patient();
        patient.setId(9);
        patient.setFirstName("Anna");
        patient.setLastName("Manou");
        patient.setDateOfBirth(LocalDate.of(2020,2,10));
        patient.setEmail("AnnaManou@gmail.com");
        patient.setPhoneNumber("6987634259");
        patient.setGender("Female");
        patient.setBloodType("O");
        patient.setAddress("Ierarxoy Gerbasiou");
        patient.setCity("Thessaloniki");
        patient.setZipCode("13567");
        patient.setCreationDate(LocalDate.of(2023,1,1));
        patient.setActive(true);
        patient.setWarning(false);
        return patient;
    }

    private void clearDB(){
        patientRepository.deleteAll();
    }


}
