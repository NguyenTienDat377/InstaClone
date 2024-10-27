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
import jakarta.persistence.UniqueConstraint;

import java.util.Date;

@Entity
@Table(name = "user_following", uniqueConstraints = {
    @UniqueConstraint (columnNames = {"followersUserId, followingUserId"})
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private int followersUserId;
    private int followingUserId;
    private Date createdAt;
}
