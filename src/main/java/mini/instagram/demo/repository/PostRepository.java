package mini.instagram.demo.repository;

import mini.instagram.demo.model.Post;
import mini.instagram.demo.model.Profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.query.Param;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByCreatedBy(Profile createdBy);

    @Query(value = "SELECT * FROM post WHERE created_by_id IN (:ids) ORDER BY created_at DESC", nativeQuery = true)
    List<Post> findByCreatedByIds(@Param("ids") List<Integer> createdByIdList);

    @Query(value = "SELECT * FROM post WHERE created_by_id IN (:ids) ORDER BY created_at DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Post> findByCreatedByIdsWithPagination(@Param("ids") List<Integer> ids, @Param("limit") int limit, @Param("offset") int offset);

    @Query(value = "SELECT COUNT(*) FROM post WHERE created_by_id IN (:ids)", nativeQuery = true)
    int countByCreatedByIn(@Param(value = "ids") List<Integer> ids);

    int countByCreatedBy(Profile createdBy);
} 
