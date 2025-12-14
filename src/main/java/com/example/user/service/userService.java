package com.example.user.service;

import com.example.user.entity.Business;
import com.example.user.entity.Role;
import com.example.user.entity.User;
import com.example.user.model.BusinessPojo;
import com.example.user.model.RolePojo;
import com.example.user.model.UserPojo;
import com.example.user.repo.businessRepo;
import com.example.user.repo.roleRepo;
import com.example.user.repo.userRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class userService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    userRepo repo;
    @Autowired
    roleRepo roleRepo1;

    @Autowired
    businessRepo businessRepo;

    @Autowired
    businessService businessService;

    public UserPojo addUser(UserPojo userPojo) {

        if (repo.existsByEmail(userPojo.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (repo.existsByMobileno(userPojo.getMobileno())) {
            throw new RuntimeException("Mobile already exists");
        }
        Business businessPojoSaved = businessService.addBusiness(userPojo.getBusinessPojo());

        User userEntity = new User();
        userEntity.setFirstname(userPojo.getFirstname());
        userEntity.setLastname(userPojo.getLastname());
        userEntity.setEmail(userPojo.getEmail());
        userEntity.setMobileno(userPojo.getMobileno());
        userEntity.setStatus(userPojo.getStatus());

        String hasherPassword = passwordEncoder.encode(userPojo.getPassword());

        userEntity.setPassword(hasherPassword);

        Role role = roleRepo1.findById(userPojo.getRoleId())
                .orElseThrow(() -> new RuntimeException("Invalid Role ID"));

        userEntity.setRole(role);

        userEntity.setBusinessid(businessPojoSaved);

        User savedUser = repo.save(userEntity);
        BusinessPojo businessPojo = userPojo.getBusinessPojo();
        businessPojo.setUserId(savedUser.getId());


        UserPojo savedPojo = new UserPojo();
        savedPojo.setId(savedUser.getId());
        savedPojo.setFirstname(savedUser.getFirstname());
        savedPojo.setLastname(savedUser.getLastname());
        savedPojo.setEmail(savedUser.getEmail());
        savedPojo.setMobileno(savedUser.getMobileno());
        savedPojo.setStatus(savedUser.getStatus());
        savedPojo.setBusinessPojo(mapEntityToPojo(savedUser.getBusinessid()));
        savedPojo.setRole(new RolePojo(savedUser.getRole().getId(), savedUser.getRole().getRole()));

        return savedPojo;
    }


//    public UserPojo getUserById(String id) {
//        User user= repo.findById(id).orElse(null);
//        ObjectMapper obj=new ObjectMapper();
//        return obj.convertValue(user, UserPojo.class);
//    }

    public Boolean deleteUser(String id) {
        User user = repo.findById(id).orElse(null);
        if (user == null) {
            return false;
        }
        user.setStatus(0);
        repo.save(user);
        return true;
    }

    public UserPojo verifyUser(UserPojo user1) {
        User user;
        if (user1.getEmail() != null) {
            user = repo.findByEmail(user1.getEmail());
            if (user == null) {
                throw new RuntimeException("no Email Exists");
            }
        } else {
            user = repo.findByMobileno(user1.getMobileno());
            if (user == null) {
                throw new RuntimeException("no Mobile No. Exists");
            }
        }

        if (!passwordEncoder.matches(user1.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }


        if (user == null || user.getStatus() == 0) {
            return null;
        }

        UserPojo userPojo = new UserPojo();
        userPojo.setId(user.getId());
        userPojo.setFirstname(user.getFirstname());
        userPojo.setLastname(user.getLastname());
        userPojo.setEmail(user.getEmail());
        userPojo.setMobileno(user.getMobileno());


        if (user.getRole() != null) {
            userPojo.setRole(new RolePojo(user.getRole().getId(), user.getRole().getRole()));
        }
        userPojo.setBusinessPojo(mapEntityToPojo(user.getBusinessid()));

        return userPojo;
    }

    public List<UserPojo> getAllUsers() {
        List<User> allUsers = repo.findAll();
        List<UserPojo> activeUsers = new ArrayList<>();
        for (User user : allUsers) {
            if (user.getStatus() != 0) {
                UserPojo userPojo = new UserPojo();
                userPojo.setId(user.getId());
                userPojo.setFirstname(user.getFirstname());
                userPojo.setLastname(user.getLastname());
                userPojo.setEmail(user.getEmail());
                userPojo.setMobileno(user.getMobileno());

                if (user.getRole() != null) {
                    userPojo.setRole(new RolePojo(user.getRole().getId(), user.getRole().getRole()));
                }
                userPojo.setBusinessPojo(mapEntityToPojo(user.getBusinessid()));
                activeUsers.add(userPojo);
            }
        }

        return activeUsers;
    }


    public BusinessPojo mapEntityToPojo(Business savedBusiness){

        BusinessPojo savedPojo = new BusinessPojo();
        savedPojo.setId(savedBusiness.getId());
         savedPojo.setBusinessName(savedBusiness.getBusinessName());
         savedPojo.setOwnerName(savedBusiness.getOwnerName());
         savedPojo.setBusinessAddress(savedBusiness.getBusinessAddress());
         savedPojo.setLicenseNo(savedBusiness.getLicenseNo());
         savedPojo.setGstNo(savedBusiness.getGstNo());
        savedPojo.setUserId(savedPojo.getUserId());
         savedPojo.setCin(savedBusiness.getCin());
         savedPojo.setBusinessModuleId(savedBusiness.getBusinessmodule().getId());
         return savedPojo;
}

}
