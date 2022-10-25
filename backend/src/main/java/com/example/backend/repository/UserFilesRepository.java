package com.example.backend.repository;

import com.example.backend.entity.Userfiles;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserFilesRepository extends CrudRepository<Userfiles, Long> {

    List<Userfiles> getByEmail(String email);


    @Transactional
    @Modifying
    void deleteByFilepath(String filepath);


    @Transactional
    @Modifying
    void deleteByFilepathAndEmail(String filepath, String email);

}