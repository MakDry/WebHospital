package EPAM.hospital.SL.entity;

public abstract class User {
    private String login;
    private String password;
    private ROLE role;

    protected User(String login, String password, ROLE role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    protected User() {
        login = "unknown";
        password = "unknown";
        role = ROLE.UNKNOWN;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public enum ROLE {
        ADMIN(1), USER(2), UNKNOWN(3);

        private int index;

        ROLE(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public ROLE getRoleByIndex(int index) {
            if (index == 1){
                return ROLE.ADMIN;
            } else if (index == 2) {
                return ROLE.USER;
            } else {
                return ROLE.UNKNOWN;
            }
        }
    }
}
