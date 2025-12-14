package com.example.user.repo;

import com.example.user.entity.BusinessModule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface businessModuleRepo extends MongoRepository<BusinessModule,String> {
}
