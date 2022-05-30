package EPAM.hospital.SL.service;

import EPAM.hospital.DAL.dao.DoctorDAO;
import EPAM.hospital.SL.model.Doctor;

import java.util.List;

public class DoctorService extends BaseService<Doctor, Integer> {

    public DoctorService() {
        super(new DoctorDAO());
    }

    @Override
    public List<Doctor> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Doctor> get(Integer id) {
        return dao.get(id);
    }

    @Override
    public boolean add(Doctor doctor) {
        return dao.add(doctor);
    }

    @Override
    public boolean remove(Integer id) {
        return dao.remove(id);
    }
}
