package EPAM.hospital.DAL.connections;

import org.slf4j.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionBuilder implements ConnectionBuilder {
    private static final Logger logger = LoggerFactory.getLogger(PoolConnectionBuilder.class);
    private DataSource resource;

    public PoolConnectionBuilder() {
        try {
            Context context = new InitialContext();
            resource = (DataSource) context.lookup("java:comp/env/jdbc/webHospital");
        } catch (NamingException e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return resource.getConnection();
    }
}
