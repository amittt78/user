package com.example.user.controller;


import com.example.user.entity.Business;
import com.example.user.model.BusinessPojo;
import com.example.user.service.businessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/business")
public class businessController {
    @Autowired
    businessService service;

    @PostMapping("/addBusiness")
    public ResponseEntity<?> addBusiness(@RequestBody BusinessPojo business){
        try{
            if(business!=null){
                Business businessPojo=service.addBusiness(business);
                return new ResponseEntity<>(businessPojo, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/removeBusiness/{id}")
    public ResponseEntity<String> removeBusiness(@PathVariable String id){
        Boolean status=service.removeBusiness(id);
        if(status){
            return new ResponseEntity<>("Sucessfully Deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Business Existed",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getBusiness")
    public ResponseEntity<List<BusinessPojo>> getBusiness(){
        return new ResponseEntity<>(service.getBusiness(),HttpStatus.OK);
    }
}
