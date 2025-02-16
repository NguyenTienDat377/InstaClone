package mini.instagram.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mini.instagram.demo.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findOneByUserId(String userId);
}
