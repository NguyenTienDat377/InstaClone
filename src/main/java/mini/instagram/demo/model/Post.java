package mini.instagram.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "post")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false)
    @JsonProperty("createdBy")
    private Profile createdBy;

    String imageUrl;
    String caption;
    private Date createdAt;

    @OneToMany(mappedBy = "post")
    @JsonProperty("comments")
    private List<Comment> comments;

    @ManyToMany
    Set<Profile> userLikes;
}