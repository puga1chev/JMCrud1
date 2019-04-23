package jmCrud.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "username", unique = false, updatable = false)
    private String username;
    @Column(name = "login", unique = true, updatable = false)
    private String login;
    @Column(name = "pass", unique = false, updatable = false)
    private String pass;

    public User() {
    }

    public User(String id, String username, String login, String pass) {
        this.id = id;
        this.username = username;
        this.login = login;
        this.pass = pass;
    }

    //    @SuppressWarnings("UnusedDeclaration")
    public String getId() {
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
}
