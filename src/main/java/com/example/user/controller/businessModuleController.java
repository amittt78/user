package com.example.user.controller;

import com.example.user.model.BusinessModulePojo;
import com.example.user.service.businessModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/businessModule")
public class businessModuleController {
    @Autowired
    businessModuleService service;

    @PostMapping("/addModule")
    public ResponseEntity<?> addModule(@RequestBody BusinessModulePojo businessModulePojo){
        try{
            if(businessModulePojo!=null){
                return new ResponseEntity<>(service.addBusinessModule(businessModulePojo),HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getModule")
    public ResponseEntity<List<BusinessModulePojo>> getModule(){
        return new ResponseEntity<>(service.getModule(),HttpStatus.OK);
    }

    @DeleteMapping("/removeModule/{id}")
    public ResponseEntity<String> removeModule(@PathVariable String id){
        Boolean status=service.removeModule(id);
        if(status){
            return new ResponseEntity<>("Succesfully Deleted",HttpStatus.OK);
        }else{
            return  new ResponseEntity<>("No Module Exists",HttpStatus.NOT_FOUND);
        }
    }
}
