package EPAM.hospital.SL.entity;

public class MedicalCard {
    private int id;
    private String diagnosis;
    private String procedures;
    private String medicines;
    private String operations;

    public MedicalCard(String diagnosis, String procedures, String medicines, String operations) {
        this.diagnosis = diagnosis;
        this.procedures = procedures;
        this.medicines = medicines;
        this.operations = operations;
    }

    public MedicalCard() {
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getProcedures() {
        return procedures;
    }

    public void setProcedures(String procedures) {
        this.procedures = procedures;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }
}