package EPAM.hospital.DAL.DoctorDAO;

import EPAM.hospital.DAL.dao.PatientDAO;
import EPAM.hospital.SL.entity.Patient;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class PatientDAOTest {

    private static PatientDAO patientDAO;
    private static TestConnectionBuilder connectionBuilder;

    @BeforeClass
    public static void prepareTest() {
        patientDAO = new PatientDAO();
        connectionBuilder = new TestConnectionBuilder();
        patientDAO.setConnectionBuilder(connectionBuilder);
    }

    @Test
    public void findAllTest() {
        List<Patient> patients = patientDAO.findAll();
        Assert.assertFalse(patients.isEmpty());
        Assert.assertEquals("Іван", patients.get(0).getName());
        Assert.assertEquals("Назаренко", patients.get(0).getSurname());
    }

    @Test
    public void getTest() {
        List<Patient> patient1 = patientDAO.get(1);
        Assert.assertFalse(patient1.isEmpty());
        Assert.assertEquals("Іван", patient1.get(0).getName());
        Assert.assertEquals("Назаренко", patient1.get(0).getSurname());

        List<Patient> patients2 = patientDAO.get(20000);
        Assert.assertTrue(patients2.isEmpty());
    }
}
