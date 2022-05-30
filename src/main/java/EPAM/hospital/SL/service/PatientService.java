package EPAM.hospital.SL.service;

import EPAM.hospital.DAL.dao.PatientDAO;
import EPAM.hospital.SL.model.Patient;

import java.util.List;

public class PatientService extends BaseService<Patient, Integer> {

    public PatientService() {
        super(new PatientDAO());
    }

    @Override
    public List<Patient> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Patient> get(Integer id) {
        return dao.get(id);
    }

    @Override
    public boolean add(Patient patient) {
        return dao.add(patient);
    }

    @Override
    public boolean remove(Integer id) {
        return dao.remove(id);
    }
}
