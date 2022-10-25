package com.example.backend.service;

import com.example.backend.entity.Userlog;
import com.example.backend.repository.UserLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogService {
    @Autowired
    private UserLogRepository userLogRepository;

    public void addUserLog(Userlog userlog){

        userLogRepository.save(userlog);
    }

    public List<Userlog> getUserlogByEmail(String email){

        return userLogRepository.getUserlogByEmail(email);
    }

}