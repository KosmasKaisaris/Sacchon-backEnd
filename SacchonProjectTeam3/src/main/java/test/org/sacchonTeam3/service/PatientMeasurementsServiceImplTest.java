package test.org.sacchonTeam3.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sacchonTeam3.dto.PatientMeasurementsDto;
import org.sacchonTeam3.exception.CreatedException;
import org.sacchonTeam3.model.PatientMeasurements;
import org.sacchonTeam3.repository.PatientMeasurementsRepository;
import org.sacchonTeam3.service.PatientMeasurementsService;
import org.sacchonTeam3.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.org.sacchonTeam3.MainTest;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest(classes = MainTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PatientMeasurementsServiceImplTest {

    @Autowired
    private PatientMeasurementsService patientMeasurementsService;

    @Autowired
    private PatientMeasurementsRepository patientMeasurementsRepository;


    @Before
    public void beforeClass() {
        clearDB();
    }

    @After
    public void afterTest() {
        clearDB();
    }

    @Test
    public void storePatientMeasurement_OK() throws CreatedException {
        PatientMeasurementsDto patientmeasurementsDto = create();
        PatientMeasurementsDto storedPatiendMeasurementsDto =patientMeasurementsService.storePatientMeasurement(patientmeasurementsDto);

        Assert.assertNotNull(storedPatiendMeasurementsDto.getId());
        Assert.assertEquals(storedPatiendMeasurementsDto.getGlucose(),83.5);
        Assert.assertEquals(storedPatiendMeasurementsDto, patientMeasurementsRepository.findById(patientmeasurementsDto.getId()));
    }

    @Test
    public void findPatientMeasurement_OK() {
        PatientMeasurementsDto patientMeasurementsDto = create();
        PatientMeasurements  patientMeasurements = MappingUtils.DtoToPatientMeasurements(patientMeasurementsDto);
        patientMeasurementsRepository.save(patientMeasurements);
        List<PatientMeasurementsDto> listPatientMeasurementDto = patientMeasurementsService.findPatientMeasurement(patientMeasurements.getId());

        Assert.assertNotNull(listPatientMeasurementDto);


    }

    @Test
    public void update_modifyPatientMeasurement_OK() throws CreatedException {
        PatientMeasurementsDto patientMeasurementsDto = create();
        PatientMeasurements  patientMeasurements = MappingUtils.DtoToPatientMeasurements(patientMeasurementsDto);
        patientMeasurementsRepository.save(patientMeasurements);
        patientMeasurementsDto.setId(1);
        patientMeasurementsDto.setGlucose(73.2);
        patientMeasurementsDto.setCarb(54);
        patientMeasurementsService.update_modifyPatientMeasurement(patientMeasurementsDto);

        Assert.assertEquals(patientMeasurementsRepository.findById(1),patientMeasurementsDto);


    }

    @Test
    public void removePatientMeasurement_OK() {
        PatientMeasurementsDto patientMeasurementsDto = create();
        PatientMeasurements  patientMeasurements = MappingUtils.DtoToPatientMeasurements(patientMeasurementsDto);
        patientMeasurementsRepository.save(patientMeasurements);
        int id = patientMeasurementsDto.getId();
        patientMeasurementsRepository.deleteById(patientMeasurementsDto.getId());

        Assert.assertEquals(null ,patientMeasurementsService.findPatientMeasurement(id));
    }


    //////////Helper Classes ////////////////



    private PatientMeasurementsDto create() {
        PatientMeasurementsDto patientMeasurementsDto = new PatientMeasurementsDto();
        patientMeasurementsDto.setId(2);
        patientMeasurementsDto.setGlucose(83.5);
        patientMeasurementsDto.setCarb(60);
        patientMeasurementsDto.setMeasurementDate(LocalDate.of(2023, 1, 28));

        return patientMeasurementsDto;

    }

    private void clearDB() {
        patientMeasurementsRepository.deleteAll();
    }
}