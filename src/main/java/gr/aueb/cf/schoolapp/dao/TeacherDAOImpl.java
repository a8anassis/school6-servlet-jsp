package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exception.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {

    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
        String sql = "INSERT INTO teachers (firstname, lastname) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Extract model info
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);

            ps.executeUpdate();
            // logging
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new TeacherDAOException("Insert SQL error. Teacher:  " + teacher + " not inserted");
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE teachers set firstname = ?, lastname = ? WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Extract model info
            int id = teacher.getId();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, id);

            ps.executeUpdate();
            // logging
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new TeacherDAOException("Update SQL error. Teacher:  " + teacher + " not updated");
        }
    }

    @Override
    public void delete(Integer id) throws TeacherDAOException {
        String sql = "DELETE FROM teachers WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

          ps.setInt(1, id);
          ps.executeUpdate();
          // Logging
        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new TeacherDAOException("Delete SQL error. Teacher with id:  " + id + " not deleted");
        }
    }

    @Override
    public Teacher getById(Integer id) throws TeacherDAOException {
        return null;
    }

    @Override
    public List<Teacher> getFilteredTeachers(String firstname, String lastname) throws TeacherDAOException {
        return null;
    }
}
