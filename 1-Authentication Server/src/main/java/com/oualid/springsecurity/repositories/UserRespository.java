package com.oualid.springsecurity.repositories;

import com.oualid.springsecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<User,Long> {

    Optional<User> findUserByUsername(String username);
}
