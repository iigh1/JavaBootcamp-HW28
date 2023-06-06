package com.example.javabootcamphw28.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter@NoArgsConstructor
public class MyUser implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username can't be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String username;

//    @NotEmpty(message = "password can't be empty")
//    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "role can't be empty")
    @Column(columnDefinition = "varchar(20) check(role='customer' or role='admin')")
    private String role;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "myUser")
    private Set<MyOrder> orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
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
