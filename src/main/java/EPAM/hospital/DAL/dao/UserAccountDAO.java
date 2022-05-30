package EPAM.hospital.DAL.dao;

import EPAM.hospital.SL.entity.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDAO extends BaseDAO<UserAccount, String> {

    private static final Logger logger = LoggerFactory.getLogger(UserAccountDAO.class);

    private enum UserAccountRequest {
        SQL_SELECT_ACCOUNTS("SELECT * " +
                "FROM users"),
        SQL_SELECT_ACCOUNT_BY_LOGIN("SELECT * " +
                "FROM users " +
                "WHERE login = ?");

        private final String request;

        UserAccountRequest(String request) {
            this.request = request;
        }
    }

    @Override
    public List<UserAccount> findAll() {
        List<UserAccount> accounts = new ArrayList<>();

        try (Connection connection = super.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(UserAccountRequest.SQL_SELECT_ACCOUNTS.request);
            accounts = handleResultForUserAccount(rs);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return accounts;
    }

    @Override
    public List<UserAccount> get(String login) {
        List<UserAccount> account = new ArrayList<>();

        try (Connection connection = super.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UserAccountRequest.SQL_SELECT_ACCOUNT_BY_LOGIN.request);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            account = handleResultForUserAccount(rs);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return account;
    }

    @Override
    public boolean add(UserAccount entity) {
        return false;
    }

    @Override
    public boolean remove(String parameter) {
        return false;
    }

    private List<UserAccount> handleResultForUserAccount(ResultSet rs) throws SQLException {
        List<UserAccount> accounts = new ArrayList<>();

        while (rs.next()) {
            UserAccount account = new UserAccount();

            account.setLogin(rs.getString("login"));
            account.setPassword(rs.getString("password"));
            account.setRole(rs.getInt("role"));

            accounts.add(account);
        }
        return accounts;
    }
}
