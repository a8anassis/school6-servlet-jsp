package gr.aueb.cf.schoolapp.model;

import gr.aueb.cf.schoolapp.core.RoleType;

public class User {
    private Integer id;
    private String username;
    private String password;
    private RoleType roleType;

    public User() {}

    public User(Integer id, String username, String password, RoleType roleType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleType = roleType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role ='" + roleType.name() + '\'' +
                '}';
    }
}
