package mini.instagram.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mini.instagram.demo.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    
}
