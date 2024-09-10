package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.validator.TeacherValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/teachers/update")
public class TeacherUpdateController extends HttpServlet {

    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer id = Integer.parseInt(req.getParameter("id").trim());
        String firstname = req.getParameter("firstname").trim();
        String lastname = req.getParameter("lastname").trim();

        TeacherUpdateDTO updateDTO = new TeacherUpdateDTO(id, firstname,lastname);
        req.setAttribute("updateDTO", updateDTO);
        req.getRequestDispatcher("/WEB-INF/jsp/teacher-update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer id = Integer.parseInt(req.getParameter("id").trim());
        String firstname = req.getParameter("firstname").trim();
        String lastname = req.getParameter("lastname").trim();

        TeacherUpdateDTO updateDTO = new TeacherUpdateDTO(id, firstname, lastname);
        Map<String, String> errors;
        String firstnameMessage;
        String lastnameMessage;
        String errorMessage;
        Teacher teacher;

        try {
            // Validate dto
            errors = TeacherValidator.validate(updateDTO);

            if (!errors.isEmpty()) {
                firstnameMessage = errors.getOrDefault("firstname", "");
                lastnameMessage = errors.getOrDefault("lastname", "");

                req.setAttribute("firstnameMessage", firstnameMessage);
                req.setAttribute("lastnameMessage", lastnameMessage);
                req.setAttribute("updateDTO", updateDTO);
                req.getRequestDispatcher("/WEB-INF/jsp/teacher-update.jsp")
                        .forward(req, resp);
                return;
            }

            // Call the service
            teacher = teacherService.updateTeacher(updateDTO);
            TeacherReadOnlyDTO readOnlyDTO = mapToReadOnlyDTO(teacher);
            req.setAttribute("teacherInfo", readOnlyDTO);
            req.getRequestDispatcher("/WEB-INF/jsp/teacher-updated.jsp")
                    .forward(req, resp);
        } catch (TeacherNotFoundException | TeacherDAOException e) {
            errorMessage = e.getMessage();
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/WEB-INF/jsp/teacher-update.jsp")
                    .forward(req, resp);
        }
    }

    private TeacherReadOnlyDTO mapToReadOnlyDTO(Teacher teacher) {
        return new TeacherReadOnlyDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname());
    }
}