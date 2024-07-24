package com.timskiproekt.pagepicks.model;

import jakarta.persistence.*;
<<<<<<< Updated upstream

import java.util.Set;
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
>>>>>>> Stashed changes

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
<<<<<<< Updated upstream
public class User {
=======
public class User implements UserDetails {

>>>>>>> Stashed changes
    @Id
    @GeneratedValue
    private Integer id;

<<<<<<< Updated upstream
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
=======
    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
>>>>>>> Stashed changes
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
