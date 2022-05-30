package EPAM.hospital.DAL.dao;

import EPAM.hospital.SL.model.Doctor;
import EPAM.hospital.SL.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO extends BaseDAO<Doctor, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(DoctorDAO.class);
    private enum DoctorRequest {
        SQL_SELECT_DOCTORS("SELECT * FROM doctors"),
        SQL_FIND_DOCTORS_PATIENTS("SELECT " +
                "P.id, surname, name, patronymic, date_of_birth " +
                "FROM doctors_patients DP " +
                "INNER JOIN patients P " +
                "ON DP.patient_id = P.id " +
                "WHERE DP.doctor_id = ?"),
        SQL_FIND_DOCTOR_BY_ID("SELECT * " +
                "FROM doctors " +
                "WHERE id = ?"),
        SQL_FIND_DOCTORS_BY_CATEGORY("SELECT * " +
                "FROM doctors " +
                "WHERE category = ?"),
        SQL_ADD_DOCTOR("INSERT INTO doctors(login, surname, name, patronymic, category) " +
                "VALUES(?, ?, ?, ?, ?)"),
        SQL_REMOVE_DOCTOR("DELETE FROM doctors " +
                "WHERE id = ?; " +
                "DELETE FROM users " +
                "WHERE login = ?");

        private final String request;
        DoctorRequest(String request) {
            this.request = request;
        }
    }
    @Override
    public List<Doctor> findAll() {
        List<Doctor> findDoctors = new ArrayList<>();

        try (Connection connection = super.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(DoctorRequest.SQL_SELECT_DOCTORS.request);
            findDoctors = handleResultForDoctor(rs);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return findDoctors;
    }

    @Override
    public List<Doctor> get(Integer id) {
        List<Doctor> doctor = null;

        try (Connection connection = super.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DoctorRequest.SQL_FIND_DOCTOR_BY_ID.request);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            doctor = handleResultForDoctor(rs);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return doctor;
    }

    @Override
    public boolean add(Doctor doctor) {
        try (Connection connection = super.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DoctorRequest.SQL_ADD_DOCTOR.request);
            ps.setString(1, doctor.getAccount().getLogin());
            ps.setString(2, doctor.getSurname());
            ps.setString(3, doctor.getName());
            ps.setString(4, doctor.getPatronymic());
            ps.setInt(5, doctor.getCategory().getIndex());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean remove(Integer id) {
        String doctorLogin = get(id).get(0).getAccount().getLogin();

        try (Connection connection = super.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DoctorRequest.SQL_REMOVE_DOCTOR.request);
            ps.setInt(1, id);
            ps.setString(2, doctorLogin);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return true;
    }

    public List<Doctor> getDoctorsByCategory(int categoryIndex) {
        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = super.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DoctorRequest.SQL_FIND_DOCTORS_BY_CATEGORY.request);
            ps.setInt(1, categoryIndex);
            ResultSet rs = ps.executeQuery();
            doctors = handleResultForDoctor(rs);
        } catch (SQLException e) {
              logger.warn(e.getMessage());
        }
        return doctors;
    }

    private List<Patient> getDoctorsPatients(int doctorsId) {
        List<Patient> patients = new ArrayList<>();

        try (Connection connection = super.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DoctorRequest.SQL_FIND_DOCTORS_PATIENTS.request);
            ps.setInt(1, doctorsId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setSurname(rs.getString("surname"));
                patient.setName(rs.getString("name"));
                patient.setPatronymic(rs.getString("patronymic"));
                patient.setDateOfBirth(rs.getDate("date_of_birth"));

                patients.add(patient);
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return patients;
    }

    private List<Doctor> handleResultForDoctor(ResultSet rs) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        while (rs.next()) {
            Doctor doctor = new Doctor();
            int id = rs.getInt("id");
            doctor.setId(id);
            doctor.setSurname(rs.getString("surname"));
            doctor.setName(rs.getString("name"));
            doctor.setPatronymic(rs.getString("patronymic"));
            doctor.getAccount().setLogin(rs.getString("login"));
            doctor.setCategory(rs.getInt("category"));
            List<Patient> patients = getDoctorsPatients(id);
            doctor.setPatients(patients);

            doctors.add(doctor);
        }
        return doctors;
    }
}
