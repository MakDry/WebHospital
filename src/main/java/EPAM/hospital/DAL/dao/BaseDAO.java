package EPAM.hospital.DAL.dao;

import EPAM.hospital.DAL.connections.ConnectionBuilder;
import EPAM.hospital.DAL.connections.PoolConnectionBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T, K> {

    private ConnectionBuilder connectionBuilder = new PoolConnectionBuilder();
    public abstract List<T> findAll() throws SQLException;
    public abstract List<T> get(K parameter) throws SQLException;
    public abstract boolean add(T entity) throws SQLException;
    public abstract boolean remove(K parameter) throws SQLException;
    protected Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {  //   CHECK IF IT USED
        this.connectionBuilder = connectionBuilder;
    }
}
