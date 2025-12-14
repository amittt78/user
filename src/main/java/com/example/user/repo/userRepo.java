package com.example.user.repo;

import com.example.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends MongoRepository<User,String> {
    User findByEmail(String email);
    User findByMobileno(String mobileno);
    Boolean existsByEmail(String email);
    Boolean existsByMobileno(String Mobileno);
}
