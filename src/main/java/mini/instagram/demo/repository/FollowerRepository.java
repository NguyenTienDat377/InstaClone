package mini.instagram.demo.repository;

import mini.instagram.demo.model.UserFollowing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface FollowerRepository extends JpaRepository<UserFollowing, Integer> {
    @Query(value = "SELECT * FROM user_following WHERE follower_user_id = :followerUserId", nativeQuery = true)
    List<UserFollowing> findByFollowerUserId(@Param(value = "followerUserId") int followerUserId);

    int countByFollowerUserId(int followerUserId);

    @Query(value = "SELECT * FROM user_following WHERE following_user_id = followingUserId", nativeQuery = true)
    List<UserFollowing> findByFollowingUserId(@Param(value = "followingUserId") int followingUserId);

    int countByFollowingUser(int followerUserId);

    UserFollowing findByFollowerUserIdAndFollowingUserId(int followerUserId, int followingUserId);

    void deleteByFollowerUserIdAndFollowingUserId(int followerUseId, int followingUserId);

}
