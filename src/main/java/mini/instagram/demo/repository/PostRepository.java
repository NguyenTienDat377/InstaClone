package mini.instagram.demo.repository;

import mini.instagram.demo.model.Post;
import mini.instagram.demo.model.Profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByCreatedBy(Profile createdBy);

    @Query(value = "SELECT * FROM post WHERE created_by_id IN (:ids) ORDER BY created_date", nativeQuery = true)
    List<Post> findByCreatedBy(@Param("ids") List<Integer> createdByIdList);

    @Query(value = "SELECT COUNT(*) FROM post WHERE created_by_id IN (:ids)", nativeQuery = true)
    int countByCreatedByIn(@Param(value = "ids") List<Integer> ids);

    int countByCreatedBy(Profile createdBy);
} 
