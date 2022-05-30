package EPAM.hospital.DAL.DoctorDAO;

import EPAM.hospital.DAL.dao.DoctorDAO;
import EPAM.hospital.SL.model.Doctor;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class DoctorDAOTest {

    private static DoctorDAO doctorDAO;
    private static TestConnectionBuilder connectionBuilder;

    @BeforeClass
    public static void prepareTest() {
        doctorDAO = new DoctorDAO();
        connectionBuilder = new TestConnectionBuilder();
        doctorDAO.setConnectionBuilder(connectionBuilder);
    }

    @Test
    public void findAllTest() {
        List<Doctor> doctors = doctorDAO.findAll();
        Assert.assertFalse(doctors.isEmpty());
        Doctor doctor = doctors.get(0);
        Assert.assertEquals("Максим", doctor.getName());
        Assert.assertEquals(1, doctor.getId());
    }

    @Test
    public void getTest() {
        List<Doctor> doctor1 = doctorDAO.get(1);
        Assert.assertFalse(doctor1.isEmpty());
        Assert.assertEquals(1, doctor1.get(0).getId());
        Assert.assertEquals("Максим", doctor1.get(0).getName());

        List<Doctor> doctor2 = doctorDAO.get(20000);
        Assert.assertTrue(doctor2.isEmpty());
    }

    @Test
    public void getDoctorByCategoryTest() {
        List<Doctor> doctors = doctorDAO.getDoctorsByCategory(3);
        Assert.assertFalse(doctors.isEmpty());
        Assert.assertEquals("Максим", doctors.get(0).getName());
        Assert.assertEquals(1, doctors.get(0).getId());
    }
}
