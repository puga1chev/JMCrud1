package jmCrud.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = false, updatable = true)
    private String username;
    @Column(name = "login", unique = true, updatable = true)
    private String login;
    @Column(name = "pass", unique = false, updatable = true)
    private String pass;
    @ManyToOne
    @JoinColumn(name = "role_id", unique = false, updatable = true)
    private Role role;

    public User() {
    }

    public User(Long id, String username, String login, String pass, Role role) {
        this.id = id;
        this.username = username;
        this.login = login;
        this.pass = pass;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
