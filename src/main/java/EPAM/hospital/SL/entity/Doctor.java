package EPAM.hospital.SL.entity;

public class Doctor extends User {
    private int id;
    private String surname;
    private String name;
    private String patronymic;

    public Doctor(String login, String password, ROLE role, String surname, String name, String patronymic) {
        super(login, password, role);
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public enum CATEGORY {
        PEDIATRICIAN(1, "Педіатр"),
        TRAUMA_SURGEON(2, "Травматолог"),
        SURGEON(3, "Хірург"),
        NURSE(4, "Медсестра");

        private int index;
        private String description;

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

        public CATEGORY getCategoryByIndex(int index) {
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
    }
}
