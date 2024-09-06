package gr.aueb.cf.schoolapp.dto;

public class UserReadOnlyDTO extends BaseUserDTO {

    public UserReadOnlyDTO() {}

    public UserReadOnlyDTO(String username, String password, String confirmedPassword) {
        super(username, password, confirmedPassword);
    }
}
