package com.example.user.repo;

import com.example.user.entity.Business;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface businessRepo extends MongoRepository<Business,String> {
}
