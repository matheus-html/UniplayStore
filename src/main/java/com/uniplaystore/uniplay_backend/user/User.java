package com.uniplaystore.uniplay_backend.user;

import lombok.*;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String login;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private UserRole role;

    @Override
    public String toString() {
        return "User {" + "\n" +
                "Id: " + id + "\n" +
                "name: " + login + "\n" +
                "email: " + email + "\n" +
                "password: " + password + "\n" +
                '}';
    }

    public User(String login, String email, String password, UserRole role) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ROLE_ADMIN) return List.of(
                new SimpleGrantedAuthority(UserRole.ROLE_ADMIN.name()),
                new SimpleGrantedAuthority(UserRole.ROLE_USER.name()));
        else return List.of(new SimpleGrantedAuthority(UserRole.ROLE_USER.name()));
    }


    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
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
