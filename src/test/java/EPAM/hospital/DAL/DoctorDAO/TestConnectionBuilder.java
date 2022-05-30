package EPAM.hospital.DAL.DoctorDAO;

import EPAM.hospital.DAL.connections.ConnectionBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnectionBuilder implements ConnectionBuilder {
    ResourceBundle resource = ResourceBundle.getBundle("testDatabase");
    private final String url = resource.getString("db.url");
    private final String user = resource.getString("db.user");
    private final String password = resource.getString("db.password");
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
