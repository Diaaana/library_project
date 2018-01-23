package by.radomskaya.project.logic;

import by.radomskaya.project.dao.AdminDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.DAOException;

public class AdminLogic {

    public boolean addAdmin(User admin) throws DAOException {
        AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
        return adminDAO.addAdmin(admin);
    }

    public boolean checkAdmin(String login, String password) throws DAOException {
        AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
        return adminDAO.checkLoginPasswordAdmin(login, password);
    }

}
