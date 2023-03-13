package test.org.sacchonTeam3.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sacchonTeam3.dto.ConsultationDto;
import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.exception.DatabaseException;
import org.sacchonTeam3.model.Consultation;
import org.sacchonTeam3.repository.ConsultationRepository;
import org.sacchonTeam3.service.ConsultationService;
import org.sacchonTeam3.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.org.sacchonTeam3.MainTest;

import java.time.LocalDate;

@SpringBootTest(classes = MainTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ConsultationServiceImplTest {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private ConsultationRepository consultationRepository;



    @Test
    public void findPatientConsultation_OK(){

    }

    @Test
    public void createConsultation_OK() throws CreatedException, DatabaseException {
    Consultation consultation = create();
    ConsultationDto consultationDto = MappingUtils.ConsultationToDto(consultation);
    consultationService.createConsultation(consultationDto);


    Assert.assertNotNull(consultationRepository.findById(consultationDto.getId()));
    Assert.assertEquals(consultationDto.getMedicationName(),"Panadol");
    Assert.assertEquals(consultationDto.getMedicationDosage(),55);
    Assert.assertEquals(consultationDto, consultationRepository.findById(consultation.getId()));

    }

    @Test
    public void update_modifyConsultationOfPatient_OK(){

    }

    @Test
    public void findDoctorConsultation_OK(){

    }


    @Before
    public void beforeClass(){
        clearDB();
    }

    @After
    public void afterTest(){
        clearDB();
    }


    //////////Helper Classes ////////////////
    private Consultation create(){
        Consultation consultation = new Consultation();
        consultation.setId(9);
        consultation.setConsultationDate(LocalDate.of(2023,3,2));
        consultation.setMedicationName("Panadol");
        consultation.setMedicationDosage(55);
        consultation.setMedicalReport("If you take your medication dosage for 1 week you will get better");

        return consultation;
    }


    private void clearDB(){
        consultationRepository.deleteAll();
    }

}
