package mini.instagram.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

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
    private int user;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    @JsonProper


}