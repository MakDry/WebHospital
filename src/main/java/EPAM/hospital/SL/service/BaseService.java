package EPAM.hospital.SL.service;

import EPAM.hospital.DAL.dao.BaseDAO;

import java.util.List;

abstract class BaseService <T, K>{
    protected BaseDAO<T, K> dao;

    protected BaseService(BaseDAO<T, K> dao) {
        this.dao = dao;
    }

    public abstract List<T> findAll();
    public abstract List<T> get(K parameter);
    public abstract boolean add(T entity);
    public abstract boolean remove(K parameter);
}
