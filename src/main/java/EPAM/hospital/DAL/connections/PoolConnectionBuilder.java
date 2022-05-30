package EPAM.hospital.DAL.connections;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionBuilder implements ConnectionBuilder {
    private DataSource resource;

    public PoolConnectionBuilder() {
        try {
            Context context = new InitialContext();
            resource = (DataSource) context.lookup("java:comp/env/jdbc/webHospital");
        } catch (NamingException e) {
            e.printStackTrace();                                 // USE LOGGER THERE
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return resource.getConnection();
    }
}
