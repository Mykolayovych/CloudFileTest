package com.example.backend.repository;

import com.example.backend.entity.Groups;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupRepository extends CrudRepository<Groups, Long> {

    List<Groups> getGroupsByOwner(String email);

    @Transactional
    @Modifying
    void deleteByGroupid(Integer id);

    Groups getByGroupid(Integer id);

}