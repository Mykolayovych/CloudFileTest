package com.example.backend.service;

import com.example.backend.entity.Userfiles;
import com.example.backend.repository.UserFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFilesService {
    @Autowired
    private UserFilesRepository userFilesRepository;

    public void addUserFile(Userfiles userfiles){
        userFilesRepository.save(userfiles);
    }

    public List<Userfiles> getUserFilesByEmail(String email){
       return userFilesRepository.getByEmail(email);
    }

    public void deleteUserFilesByFilepath(String filepath){
        userFilesRepository.deleteByFilepath(filepath);
    }

    public void deleteUserFilesByEmailAndFilepath(String filepath, String email){
        userFilesRepository.deleteByFilepathAndEmail(filepath, email);
    }

}