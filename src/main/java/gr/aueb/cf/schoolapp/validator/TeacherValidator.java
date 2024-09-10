package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.BaseDTO;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidator<T> {

    private TeacherValidator() {

    }

    public static <T extends BaseDTO> Map<String, String> validate(T dto) {
        Map<String, String> errors = new HashMap<>();

        if (dto.getFirstname().length() < 2 || dto.getFirstname().length() > 32) {
            errors.put("firstname", "Το όνομα πρέπει να είναι 2 και 32 χαρακτήρες");
        }

        if (dto.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "Το όνομα δεν πρέπει να περιλαμβάνει κενά");
        }

        if (dto.getLastname().length() < 2 || dto.getLastname().length() > 32) {
            errors.put("lastname", "Το επώνυμο πρέπει να είναι 2 και 32 χαρακτήρες");
        }

        if (dto.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "Το επώνυμο δεν πρέπει να περιλαμβάνει κενά");
        }
        return errors;
    }
}
