package com.marstafk.IHMtrackerTool.repositories;


import com.marstafk.IHMtrackerTool.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
}
