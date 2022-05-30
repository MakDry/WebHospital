package EPAM.hospital.SL.model;

import java.sql.Date;

public class Patient extends Person {

    private MedicalCard medicalCard;
    private Date dateOfBirth;

    public Patient(String surname, String name, String patronymic, Date dateOfBirth) {
        super(surname, name, patronymic);
        this.dateOfBirth = dateOfBirth;
    }

    public Patient() {
        super(null, null, null);                      // SHOULD BE REWRITTEN
    }

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void addMedicalCard(int id, String diagnosis, String procedures, String medicines, String operations) {
        medicalCard = new MedicalCard(id, diagnosis, procedures, medicines, operations);
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
