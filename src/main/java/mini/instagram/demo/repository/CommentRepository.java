package mini.instagram.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mini.instagram.demo.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value = "SELECT * FROM comment WHERE post_id = :postId ORDER BY created_at DESC LIMIT :limit", nativeQuery = true)
    List<Comment> findAllCommentWithLimit(@Param("postId") int postId, @Param("limit") int limit);
}
