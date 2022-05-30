package EPAM.hospital.DAL.dao;

import EPAM.hospital.SL.entity.MedicalCard;
import EPAM.hospital.SL.entity.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO extends BaseDAO<Patient, Integer> {
    private enum PatientRequest {
        SQL_SELECT_PATIENTS("SELECT P.id, surname, name, patronymic, date_of_birth, " +
                "MC.diagnosis, procedures, medicines, operations " +
                "FROM patients P " +
                "INNER JOIN medical_cards MC " +
                "ON P.medical_card_id = MC.id"),
        SQL_FIND_PATIENT_BY_ID("SELECT P.id, surname, name, patronymic, date_of_birth, " +
                "MC.id AS 'medical_card_id', diagnosis, procedures, medicines, operations " +
                "FROM patients P " +
                "INNER JOIN medical_cards MC " +
                "ON P.medical_card_id = MC.id " +
                "WHERE P.id = ?"),
        SQL_ADD_PATIENT("INSERT INTO patients(medical_card_id, surname, name, patronymic, date_of_birth) " +
                "VALUES(?, ?, ?, ?, ?)"),
        SQL_ADD_MEDICAL_CARD("INSERT INTO medical_cards(diagnosis, procedures, medicines, operations) " +
                "VALUES('', '', '', '')"),
        SQL_REMOVE_PATIENT("DELETE FROM patients " +
                "WHERE id = ?"),
        SQL_SELECT_MEDICAL_CARDS("SELECT * " +
                "FROM medical_cards"),
        SQL_REMOVE_MEDICAL_CARD_BY_ID("DELETE FROM medical_cards " +
                "WHERE id = ?"),
        SQL_UPDATE_MEDICAL_CARD("UPDATE medical_cards " +
                "SET diagnosis = ?, procedures = ?, medicines = ?, operations = ? " +
                "WHERE id = ?");
        private final String request;

        PatientRequest(String request) {
            this.request = request;
        }
    }
    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();

        try (Connection connection = super.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(PatientRequest.SQL_SELECT_PATIENTS.request);
            patients = handleResultForPatient(rs);
        } catch (SQLException e) {
                                                                       // NEED LOGGING HERE
        }
        return patients;
    }

    @Override
    public List<Patient> get(Integer id) {
        List<Patient> patient = new ArrayList<>();

        try (Connection connection = super.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(PatientRequest.SQL_FIND_PATIENT_BY_ID.request);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            patient = handleResultForPatient(rs);
        } catch (SQLException e) {
                                                                      // NEED LOGGING HERE
        }
        return patient;
    }

    @Override
    public boolean add(Patient patient) throws SQLException {
        try (Connection connection = super.getConnection()) {
            addMedicalCard();
            int medCardId = getPatientCardId();

            PreparedStatement ps = connection.prepareStatement(PatientRequest.SQL_ADD_PATIENT.request);
            ps.setInt(1, medCardId);
            ps.setString(2, patient.getSurname());
            ps.setString(3, patient.getName());
            ps.setString(4, patient.getPatronymic());
            ps.setDate(5, patient.getDateOfBirth());
            ps.executeUpdate();
        } catch (SQLException e) {
                                                                      // NEED LOGGING HERE
        }
        return true;
    }

    @Override
    public boolean remove(Integer id) {

        try (Connection connection = super.getConnection()) {
            Patient patient = get(id).get(0);

            PreparedStatement ps = connection.prepareStatement(PatientRequest.SQL_REMOVE_PATIENT.request);
            ps.setInt(1, id);
            ps.executeUpdate();
            removeMedicalCard(patient.getMedicalCard().getId());
        } catch (SQLException e) {
                                                                             // NEED LOGGING HERE
        }
        return true;
    }

    private int getPatientCardId() throws SQLException {
        int lastMedCardId = 0;

        try (Connection connection = super.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(PatientRequest.SQL_SELECT_MEDICAL_CARDS.request);

            while(rs.next()) {
                lastMedCardId = rs.getInt("id");
            }
        }
        return lastMedCardId;
    }

    private boolean addMedicalCard() throws SQLException {
        try (Connection connection = super.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(PatientRequest.SQL_ADD_MEDICAL_CARD.request);
            ps.executeUpdate();
        }
        return true;
    }

    private boolean updateMedicalCard(Integer patientId, MedicalCard medicalCard) {
        Patient patient = get(patientId).get(0);

        try (Connection connection = super.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(PatientRequest.SQL_UPDATE_MEDICAL_CARD.request);
            ps.setString(1, medicalCard.getDiagnosis());
            ps.setString(2, medicalCard.getProcedures());
            ps.setString(3, medicalCard.getMedicines());
            ps.setString(4, medicalCard.getOperations());
            ps.setInt(5, patient.getMedicalCard().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
                                                                                          // NEED LOGGING HERE
        }
        return true;
    }

    private void removeMedicalCard(Integer id) throws SQLException {
        try (Connection connection = super.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(PatientRequest.SQL_REMOVE_MEDICAL_CARD_BY_ID.request);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private List<Patient> handleResultForPatient(ResultSet rs) throws SQLException {
        List<Patient> patients = new ArrayList<>();

        while (rs.next()) {
            Patient patient = new Patient();

            patient.setId(rs.getInt("id"));
            patient.setSurname(rs.getString("surname"));
            patient.setName(rs.getString("name"));
            patient.setPatronymic(rs.getString("patronymic"));
            patient.setDateOfBirth(rs.getDate("date_of_birth"));

            int cardId = rs.getInt("medical_card_id");
            String diagnosis = rs.getString("diagnosis");
            String procedures = rs.getString("procedures");           // CARD ADDING CAN BE EXTRACTED TO ANOTHER METHOD
            String medicines = rs.getString("medicines");
            String operations = rs.getString("operations");
            patient.addMedicalCard(cardId, diagnosis, procedures, medicines, operations);

            patients.add(patient);
        }
        return patients;
    }
}
