package com.example.user.service;

import com.example.user.entity.BusinessModule;
import com.example.user.model.BusinessModulePojo;
import com.example.user.repo.businessModuleRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class businessModuleService {
    @Autowired
    businessModuleRepo repo;




    public BusinessModulePojo addBusinessModule(BusinessModulePojo businessModulePojo) {
        ObjectMapper obj=new ObjectMapper();
        BusinessModule module=obj.convertValue(businessModulePojo,BusinessModule.class);
        BusinessModule savedModule=repo.save(module);
        return obj.convertValue(savedModule,BusinessModulePojo.class);
    }

    public List<BusinessModulePojo> getModule() {
        List<BusinessModule> module=repo.findAll();
        List<BusinessModulePojo> modulePojo=new ArrayList<>();
        ObjectMapper obj=new ObjectMapper();
        for(BusinessModule i : module){
            modulePojo.add(obj.convertValue(i, BusinessModulePojo.class));
        }
        return modulePojo;

    }


    public Boolean removeModule(String id) {
        if(repo.findById(id).orElse(null)!=null){
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
