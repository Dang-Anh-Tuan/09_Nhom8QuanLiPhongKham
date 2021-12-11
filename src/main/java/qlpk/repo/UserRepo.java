package qlpk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import qlpk.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("SELECT u from User u where u.userName = :userName")
    User findByUserName(@Param("userName") String userName);
}
