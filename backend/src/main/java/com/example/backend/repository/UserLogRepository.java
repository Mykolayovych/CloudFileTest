package com.example.backend.repository;

import com.example.backend.entity.Userlog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserLogRepository extends CrudRepository<Userlog, Long> {

    List<Userlog> getUserlogByEmail(String email);
}