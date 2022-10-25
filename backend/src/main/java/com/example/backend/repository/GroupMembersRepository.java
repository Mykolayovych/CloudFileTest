package com.example.backend.repository;

import com.example.backend.entity.Groupmembers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface GroupMembersRepository extends CrudRepository<Groupmembers, Long> {

    @Transactional
    @Modifying
    void deleteByGroupid(Integer id);

    @Transactional
    @Modifying
    void deleteByGroupidAndEmail(Integer groupId, String email);


    List<Groupmembers> getByGroupid(Integer id);

    List<Groupmembers> getByEmail(String email);

    Groupmembers getByEmailAndGroupid(String email, Integer groupId);


}