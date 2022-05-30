package EPAM.hospital.SL.model;

public class UserAccount {
    private String login;
    private String password;
    private ROLE role;

    public UserAccount(String login, String password, int roleId) {
        this.login = login;
        this.password = password;
        this.role = getRoleByIndex(roleId);
    }

    public UserAccount() {
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

    public void setRole(int roleIndex) {
        role = getRoleByIndex(roleIndex);
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
    public enum ROLE {
        ADMIN(1), USER(2), UNKNOWN(3);

        private int index;

        ROLE(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
}
