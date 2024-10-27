package mini.instagram.demo.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "profiles")
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(unique = true, name ="userId", nullable = false)
    String userId;
    String profileUserImg;
    String displayName;
    String username;
    String bio;
}
