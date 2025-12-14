package com.example.user.controller;

import com.example.user.model.UserPojo;
import com.example.user.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class userController {

    @Autowired
    userService service;

    @PostMapping("/userRegistration")
    public ResponseEntity<?> addUser(@RequestBody UserPojo user){
        try{


            if(user!=null){
                UserPojo userPojo=service.addUser(user);
                return new ResponseEntity<>(userPojo,HttpStatus.CREATED);
            }

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/removeUser/{id}")
    public ResponseEntity<String> removeUser(@PathVariable String id){
        Boolean user=service.deleteUser(id);
        if(user==false){
            return new ResponseEntity<>("No User Existed",HttpStatus.BAD_REQUEST);
        }else{
            return  new ResponseEntity<>("User Succesfuly Deleted",HttpStatus.OK);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserPojo user){
        UserPojo user1=service.verifyUser(user);
        if(user1==null){
            return new ResponseEntity<>("No User Existed",HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(user1,HttpStatus.OK);
        }
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserPojo>> getusers(){
        return new ResponseEntity<>(service.getAllUsers(),HttpStatus.OK);
    }


}
