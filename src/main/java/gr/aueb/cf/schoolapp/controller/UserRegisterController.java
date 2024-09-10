package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.InsertUserDTO;
import gr.aueb.cf.schoolapp.dto.UserReadOnlyDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.validator.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/users/register")
public class UserRegisterController extends HttpServlet {

    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/user-register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InsertUserDTO insertUserDTO;
        // Data Binding

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String confirmPassword = request.getParameter("confirmPassword").trim();

        String errorMessage = "";
        Map<String, String> errors;

        String usernameMessage;
        String passwordMessage;
        String confirmPasswordMessage;

        User user;

        try {
            insertUserDTO = new InsertUserDTO(username, password, confirmPassword);
            errors = UserValidator.validate(insertUserDTO);

            if (!errors.isEmpty()) {
                usernameMessage = errors.getOrDefault("username", "");
                passwordMessage = errors.getOrDefault("password", "");
                confirmPasswordMessage = errors.getOrDefault("confirmPassword", "");

                request.setAttribute("usernameMessage", usernameMessage);
                request.setAttribute("passwordMessage", passwordMessage);
                request.setAttribute("confirmPasswordMessage", confirmPasswordMessage);

                request.setAttribute("userRegisterDTO", insertUserDTO);
                request.getRequestDispatcher("/WEB-INF/jsp/user-register.jsp")
                        .forward(request, response);
                return;
            }

            user = userService.insertUser(insertUserDTO);
            UserReadOnlyDTO readOnlyDTO = mapToReadOnlyDTO(user);

            request.setAttribute("userInfo", readOnlyDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/user-registered.jsp")
                    .forward(request, response);

        } catch (UserDAOException e) {
            errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/jsp/user-register.jsp")
                    .forward(request, response);
        }

    }

    private UserReadOnlyDTO mapToReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(), user.getUsername(), user.getPassword());
    }
}
