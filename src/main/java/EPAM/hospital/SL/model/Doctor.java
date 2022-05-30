package EPAM.hospital.SL.model;

import java.util.List;

public class Doctor extends Person {

    private UserAccount account;
    private CATEGORY category;
    private List<Patient> patients;

    // Конструткор для додавання нових лікарів
    public Doctor(String surname, String name, String patronymic, int categoryIndex) {
        super(surname, name, patronymic);
        category = getCategoryByIndex(categoryIndex);
    }

    public Doctor() {
        super(null, null, null);                          // SHOULD BE REWRITTEN
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(String login, String password, int roleId) {
        this.account = new UserAccount(login, password, roleId);
    }

    public CATEGORY getCategory() {
        return category;
    }

    public void setCategory(int index) {
        category = getCategoryByIndex(index);
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    private CATEGORY getCategoryByIndex(int index) {
        if (index == 1){
            return CATEGORY.PEDIATRICIAN;
        } else if (index == 2) {
            return CATEGORY.TRAUMA_SURGEON;
        } else if (index == 3){
            return CATEGORY.SURGEON;
        } else if (index == 4) {
            return CATEGORY.NURSE;
        }
        return null;                                        // REPLACE IT
    }

    public enum CATEGORY {
        PEDIATRICIAN(1, "Педіатр"),
        TRAUMA_SURGEON(2, "Травматолог"),
        SURGEON(3, "Хірург"),
        NURSE(4, "Медсестра");

        private final int index;
        private final String description;

        CATEGORY(int index, String description) {
            this.index = index;
            this.description = description;
        }

        public int getIndex() {
            return index;
        }

        public String getDescription() {
            return description;
        }
    }
}
