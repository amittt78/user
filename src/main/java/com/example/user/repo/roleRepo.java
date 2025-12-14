package com.example.user.repo;

import com.example.user.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface roleRepo extends MongoRepository<Role,String> {

}
