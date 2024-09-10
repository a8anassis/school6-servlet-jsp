package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.FiltersDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/teachers")
public class TeachersViewController extends HttpServlet {

    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Teacher> teachers;
        String filterFirstname = request.getParameter("filterFirstname");
        filterFirstname = filterFirstname == null ? "" : filterFirstname;

        String filterLastname = request.getParameter("filterLastname");
        filterLastname = filterLastname == null ? "" : filterLastname;

        FiltersDTO filters = new FiltersDTO(filterFirstname, filterLastname);

        String message = "";

        try {
            teachers = teacherService.getFilteredTeachers(filters);

            if (teachers.isEmpty()) {
                request.setAttribute("message", "Teachers not found");
                request.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(request, response);
                return;
            }

            request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(request, response);
        } catch (TeacherDAOException e) {
            message = e.getMessage();
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(request, response);
        }

    }
}
