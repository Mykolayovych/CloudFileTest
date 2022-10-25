package com.example.backend.repository;

import com.example.backend.entity.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<Users, Long> {
    List<Users> findByEmailAndPassword(String email, String password);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.lastlogin = CURRENT_TIMESTAMP WHERE u.email = :email")
    void updateLastLogin(@Param("email") String email);

    Users findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.firstname = :firstname, u.lastname=:lastname," +
            "u.contact=:contact, u.interests=:interests WHERE u.email=:email")
    int updateUserDetails(@Param("firstname") String firstname,
                           @Param("lastname") String lastname,
                           @Param("contact") String contact,
                           @Param("interests") String interests,
                           @Param("email") String email);

}