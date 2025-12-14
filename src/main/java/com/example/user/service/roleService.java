package com.example.user.service;

import com.example.user.entity.Role;
import com.example.user.entity.User;
import com.example.user.model.RolePojo;
import com.example.user.model.UserPojo;
import com.example.user.repo.roleRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class roleService {
    @Autowired
    roleRepo repo;

    public List<RolePojo> getRoles() {
        List<Role> role=repo.findAll();
        List<RolePojo> role1=new ArrayList<>();
        ObjectMapper obj=new ObjectMapper();
        for(Role r:role){
            role1.add(obj.convertValue(r,RolePojo.class));
        }
        return role1;
    }

    public RolePojo addRole(RolePojo role) {
        ObjectMapper obj=new ObjectMapper();
        Role role1=obj.convertValue(role, Role.class);
        Role savedRole=repo.save(role1);
        return obj.convertValue(savedRole,RolePojo.class);

    }

    public Boolean removeRole(String id){
        if(repo.findById(id).orElse(null)!=null){
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
