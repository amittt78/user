package com.example.user.controller;


import com.example.user.model.RolePojo;
import com.example.user.service.roleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/role")
public class roleController {
    @Autowired
    roleService service;

    @GetMapping("/getRole")
    public ResponseEntity<List<RolePojo>> getRole(){
        return new ResponseEntity<>(service.getRoles(), HttpStatus.OK);
    }

    @PostMapping("/addRole")
    public ResponseEntity<?> addRole(@RequestBody RolePojo role){
        try{
            if(role!=null){
                return new ResponseEntity<>(service.addRole(role),HttpStatus.CREATED);
            }
        }catch(Exception e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("",HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping("/removeRole/{id}")
    public ResponseEntity<String> removeRole(@PathVariable String id){
        Boolean status=service.removeRole(id);
        if(status){
            return new ResponseEntity<>("Succesfully Deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Role Existedb",HttpStatus.NOT_FOUND);
        }
    }


}
