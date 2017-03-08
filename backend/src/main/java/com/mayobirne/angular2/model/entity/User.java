package com.mayobirne.angular2.model.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Christian Schuhmacher  on 28.04.2016.
 */
@Entity
@Table(name = "USER")
public class User extends AbstractEntity<Long> {

    @Id
    @Column(name = "USER_PK", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME", nullable = false, unique = true, updatable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String encodedPassword;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "USER_ROLES",
            joinColumns=@JoinColumn(name = "ROLE_PK", referencedColumnName = "USER_PK")
    )
    @Column(name = "ROLES")
    private Set<String> roles;



    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
