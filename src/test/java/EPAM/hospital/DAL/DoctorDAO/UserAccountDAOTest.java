package EPAM.hospital.DAL.DoctorDAO;

import EPAM.hospital.DAL.dao.UserAccountDAO;
import EPAM.hospital.SL.entity.UserAccount;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class UserAccountDAOTest {

    private static UserAccountDAO accountDAO;
    private static TestConnectionBuilder connectionBuilder;

    @BeforeClass
    public static void prepareTest() {
        accountDAO = new UserAccountDAO();
        connectionBuilder = new TestConnectionBuilder();
        accountDAO.setConnectionBuilder(connectionBuilder);
    }

    @Test
    public void findAllTest() {
        List<UserAccount> accounts = accountDAO.findAll();
        Assert.assertFalse(accounts.isEmpty());
        Assert.assertEquals("admin", accounts.get(0).getLogin());
        Assert.assertEquals("admin", accounts.get(0).getPassword());
        Assert.assertEquals(1, accounts.get(0).getRole().getIndex());
    }

    @Test
    public void getTest() {
        List<UserAccount> account = accountDAO.get("admin");
        Assert.assertFalse(account.isEmpty());
        Assert.assertEquals("admin", account.get(0).getPassword());
        Assert.assertEquals(1, account.get(0).getRole().getIndex());
    }
}
