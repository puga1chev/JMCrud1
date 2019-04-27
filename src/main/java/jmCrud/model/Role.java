package jmCrud.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rolename", unique = true, updatable = true)
    private String rolename;
    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

    public Role(Long id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }
    public Role() { }

    public Long getId() {
        return id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
