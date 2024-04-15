package com.timskiproekt.pagepicks.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

    @OneToMany(mappedBy = "user")
    private Set<UserBook> readlist;

    public Set<UserBook> getReadlist() {
        return readlist;
    }

    public void setReadlist(Set<UserBook> readlist) {
        this.readlist = readlist;
    }

    public Set<UserBook> getAlreadyRead() {
        return alreadyRead;
    }

    public void setAlreadyRead(Set<UserBook> alreadyRead) {
        this.alreadyRead = alreadyRead;
    }

    @OneToMany(mappedBy = "user")
    private Set<UserBook> alreadyRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
