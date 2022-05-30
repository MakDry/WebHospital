package EPAM.hospital.SL.service;

import EPAM.hospital.DAL.dao.UserAccountDAO;
import EPAM.hospital.SL.model.UserAccount;

import java.util.List;

public class UserAccountService extends BaseService<UserAccount, String> {

    public UserAccountService() {
        super(new UserAccountDAO());
    }

    @Override
    public List<UserAccount> findAll() {
        return dao.findAll();
    }

    @Override
    public List<UserAccount> get(String login) {
        return dao.get(login);
    }

    @Override
    public boolean add(UserAccount entity) {
        return false;
    }

    @Override
    public boolean remove(String parameter) {
        return false;
    }
}
