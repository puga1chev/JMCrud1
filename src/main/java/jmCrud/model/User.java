package jmCrud.model;

public class User {
    private String id;
    private String username;
    private String login;
    private String pass;

    public User(String id, String username, String login, String pass) {
        this.id = id;
        this.username = username;
        this.login = login;
        this.pass = pass;
    }


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
