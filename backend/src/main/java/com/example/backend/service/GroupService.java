package com.example.backend.service;

import com.example.backend.entity.Groups;
import com.example.backend.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public void addGroup(Groups group){
        groupRepository.save(group);
    }

    public List<Groups> getGroups(String email){

        return groupRepository.getGroupsByOwner(email);
    }

    public void deleteGroup(Integer id){
        groupRepository.deleteByGroupid(id);
    }

    public Groups getGroupByGroupId(Integer id){
        return groupRepository.getByGroupid(id);
    }

}