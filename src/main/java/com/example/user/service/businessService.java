package com.example.user.service;

import com.example.user.entity.Business;
import com.example.user.entity.BusinessModule;
import com.example.user.model.BusinessPojo;
import com.example.user.repo.businessModuleRepo;
import com.example.user.repo.businessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class businessService {
    @Autowired
    businessRepo repo;
    @Autowired
    businessModuleRepo repo1;
    public Business addBusiness(BusinessPojo business) {
        Business business1 = new Business();
        business1.setBusinessName(business.getBusinessName());
        business1.setOwnerName(business.getOwnerName());
        business1.setBusinessAddress(business.getBusinessAddress());
        business1.setLicenseNo(business.getLicenseNo());
        business1.setGstNo(business.getGstNo());
        business1.setCin(business.getCin());
        business1.setUser(business.getUserId());
        BusinessModule module = repo1.findById(business.getBusinessModuleId())
                .orElseThrow(() -> new RuntimeException("Invalid BusinessModule ID"));
        business1.setBusinessmodule(module);

        Business savedBusiness = repo.save(business1);

        /* BusinessPojo savedPojo=new BusinessPojo();
         savedPojo.setBusinessName(savedBusiness.getBusinessName());
         savedPojo.setOwnerName(savedBusiness.getOwnerName());
         savedPojo.setBusinessAddress(savedBusiness.getBusinessAddress());
         savedPojo.setLicenseNo(savedBusiness.getLicenseNo());
         savedPojo.setGstNo(savedBusiness.getGstNo());
        savedPojo.setUserId(savedPojo.getUserId());
         savedPojo.setCin(savedBusiness.getCin());
         savedPojo.setBusinessModuleId(module.getId());*/

        return savedBusiness;


    }

    public Boolean removeBusiness(String id) {
        Business busi=repo.findById(id).orElse(null);
        if(busi==null){
            return false;
        }
        return true;
    }


    public List<BusinessPojo> getBusiness() {
        List<Business> allBusiness=repo.findAll();
        List<BusinessPojo> allBusinessPojo=new ArrayList<>();
        for(Business b : allBusiness){
            BusinessPojo allBusinessPojo1=new BusinessPojo();

            allBusinessPojo1.setBusinessName(b.getBusinessName());
            allBusinessPojo1.setOwnerName(b.getOwnerName());
            allBusinessPojo1.setBusinessAddress(b.getBusinessAddress());
            allBusinessPojo1.setLicenseNo(b.getLicenseNo());
            allBusinessPojo1.setGstNo(b.getGstNo());
            allBusinessPojo1.setCin(b.getCin());
            allBusinessPojo1.setBusinessModuleId(b.getBusinessmodule().getId());
            allBusinessPojo.add(allBusinessPojo1);


        }
        return allBusinessPojo;
    }
}
