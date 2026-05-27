package mini.instagram.demo.repository;

import mini.instagram.demo.model.UserFollowing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface FollowerRepository extends JpaRepository<UserFollowing, Integer> {
    @Query(value = "SELECT * FROM user_following WHERE followers_user_id = :followersUserId", nativeQuery = true)
    List<UserFollowing> findByFollowersUserId(@Param(value = "followersUserId") int followersUserId);

    int countByFollowersUserId(int followersUserId);

    @Query(value = "SELECT * FROM user_following WHERE following_user_id = :followingUserId", nativeQuery = true)
    List<UserFollowing> findByFollowingUserId(@Param(value = "followingUserId") int followingUserId);

    int countByFollowingUserId(int followingUserId);

    UserFollowing findByFollowersUserIdAndFollowingUserId(int followersUserId, int followingUserId);

    void deleteByFollowersUserIdAndFollowingUserId(int followersUserId, int followingUserId);

}
