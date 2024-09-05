package gr.aueb.cf.schoolapp.authentication;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserLoginDTO;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;

public class AuthenticationProvider {
    private final static IUserDAO userDAO = new UserDAOImpl();
    private final static IUserService userService = new UserServiceImpl(userDAO);

    private AuthenticationProvider() {}

    public static boolean authenticate(UserLoginDTO userLoginDTO) throws UserDAOException {
        return userService.isUserValid(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }
}

