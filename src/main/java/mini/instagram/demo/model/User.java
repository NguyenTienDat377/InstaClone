package mini.instagram.demo.model;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import org.hibernate.annotations.JdbcTypeCode;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class User implements UserDetails {
    @Id
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private UUID id;

    @OneToMany
    private Set<Authority> authorities;
    
    private String password;

    private String name;

    private String picture;

    @Column(unique = true)
    private String username;

    private boolean accountNonExpired;

    private boolean accountNonLocked;
    
}
