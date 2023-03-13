package test.org.sacchonTeam3.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sacchonTeam3.dto.DoctorDto;
import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.exception.DoctorNotFoundException;
import org.sacchonTeam3.model.Doctor;
import org.sacchonTeam3.repository.DoctorRepository;
import org.sacchonTeam3.service.DoctorService;
import org.sacchonTeam3.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.org.sacchonTeam3.MainTest;

import java.time.LocalDate;

@SpringBootTest(classes = MainTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DoctorServiceImplTest {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorRepository doctorRepository;



    @Before
    public void beforeClass(){
        clearDB();
    }

    @After
    public void afterTest(){
        clearDB();
    }

    @Test
    public void findDoctor_OK() throws DoctorNotFoundException {
        Doctor doctor = create();
        doctorRepository.save(doctor);
        DoctorDto doctorDto = doctorService.findDoctor(doctor.getId());

        Assert.assertEquals(doctorDto.getFirstName(),"Kosmas");
        Assert.assertNotNull(doctorDto.getId());

    }

    @Test
    public void createDoctor_OK() throws CreatedException {
        Doctor doctor = create();
        DoctorDto doctorDto = MappingUtils.doctorToDto(doctor);
        doctorService.createDoctor(doctorDto);

        Assert.assertNotNull(doctorRepository.findById(doctorDto.getId()));
        Assert.assertEquals(doctorDto.getEmail(),"AnnaManou@gmail.com");
        Assert.assertEquals(doctorDto, doctorRepository.findById(doctor.getId()));


    }

    @Test
    public void removeDoctor_OK() throws DoctorNotFoundException {
        Doctor doctor = create();
        doctorRepository.save(doctor);
        int id = doctor.getId();
        doctorRepository.deleteById(doctor.getId());

        Assert.assertEquals(null ,doctorService.findDoctor(id));


    }

    @Test
    public void findAllPatientsWithoutConsultationInTheLastMonth_OK(){

    }

    @Test
    public void DoctorsPatients_OK(){

    }



    //////////Helper Classes ////////////////

    private void clearDB(){
        doctorRepository.deleteAll();
    }

    private Doctor create(){
        Doctor doctor = new Doctor();
        doctor.setId(7);
        doctor.setFirstName("Kosmas");
        doctor.setLastName("Baier");
        doctor.setDateOfBirth(LocalDate.of(2000,3,10));
        doctor.setEmail("AnnaManou@gmail.com");
        doctor.setPhoneNumber(698763425);
        doctor.setAddress("Ierarxoy Gerbasiou");
        doctor.setCity("Thessaloniki");
        doctor.setZipCode("13567");
        doctor.setCreationDate(LocalDate.of(2023,2,3));
        doctor.setActive(true);

        return doctor;
    }

}
