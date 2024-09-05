package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.FiltersDTO;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.List;

public class TeacherServiceImpl implements ITeacherService {

    private final ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException {
        Teacher teacher;

        try {
            teacher = mapToTeacher(dto);
            return teacherDAO.insert(teacher);
        } catch (TeacherDAOException e) {
            e.printStackTrace();
            // logging
            // rollback
            throw e;
        }
    }

    @Override
    public Teacher updateTeacher(TeacherUpdateDTO dto)
            throws TeacherNotFoundException, TeacherDAOException {
        Teacher teacher;

        try {
            teacher = mapToTeacher(dto);

            if (teacherDAO.getById(teacher.getId()) == null) {
                throw new TeacherNotFoundException(teacher);
            }

            return teacherDAO.update(teacher);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            // logging
            // rollback
            throw e;
        }
    }

    @Override
    public void deleteTeacher(Integer id) throws TeacherNotFoundException, TeacherDAOException {
        try {
            if (teacherDAO.getById(id) == null) {
                throw new TeacherNotFoundException("Teacher not found");
            }

            teacherDAO.delete(id);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            // logging
            // rollback
            throw e;
        }
    }

    @Override
    public Teacher getTeacherById(Integer id) throws TeacherNotFoundException, TeacherDAOException {
        Teacher teacher;

        try {
            teacher = teacherDAO.getById(id);
            if (teacher == null) {
                throw new TeacherNotFoundException("Teacher with id: " + id + " not found");
            }
            return teacher;
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            // logging
            // rollback
            throw e;
        }
    }

    @Override
    public List<Teacher> getFilteredTeachers(FiltersDTO filters) throws TeacherDAOException {
        List<Teacher> teachers;

        try {
            teachers = teacherDAO.getFilteredTeachers(filters.getFirstname(), filters.getLastname());
            return teachers;
        } catch (TeacherDAOException e) {
            e.printStackTrace();
            // logging
            // rollback
            throw e;
        }
    }

    private Teacher mapToTeacher(TeacherInsertDTO dto) {
        return new Teacher(null, dto.getFirstname(), dto.getLastname());
    }

    private Teacher mapToTeacher(TeacherUpdateDTO dto) {
        return new Teacher(dto.getId(), dto.getFirstname(), dto.getLastname());
    }
}
