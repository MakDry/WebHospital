package EPAM.hospital.UIL.listener;

import EPAM.hospital.SL.service.UserAccountService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private UserAccountService accountService = new UserAccountService();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
