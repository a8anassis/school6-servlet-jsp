package gr.aueb.cf.schoolapp.service.exceptions;

import gr.aueb.cf.schoolapp.model.Teacher;

import java.io.Serial;

public class TeacherNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public TeacherNotFoundException(Teacher teacher) {
        super("Teacher with id: " + teacher.getId() + " not found");
    }

    public TeacherNotFoundException(String s) {
        super(s);
    }
}
