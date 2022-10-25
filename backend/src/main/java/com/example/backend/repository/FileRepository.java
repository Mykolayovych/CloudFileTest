
package com.example.backend.repository;

import com.example.backend.entity.Files;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface FileRepository extends CrudRepository<Files, Long> {

    Files getByFilepathAndFileparent(String filepath, String fileparent);

    List<Files> getByFileparent(String filepath);

    @Transactional
    @Modifying
    void deleteByFilepath(String filepath);

    @Transactional
    @Modifying
    @Query("UPDATE Files f SET f.sharedcount = :sharedcount WHERE f.filepath = :filepath")
    void updateSharedCount(@Param("filepath") String filepath, @Param("sharedcount") Integer sharedcount );

    @Transactional
    @Modifying
    @Query("UPDATE Files f SET f.starred = :starred WHERE f.filepath = :filepath")
    void markStar(@Param("filepath") String filepath, @Param("starred") String starred );


}