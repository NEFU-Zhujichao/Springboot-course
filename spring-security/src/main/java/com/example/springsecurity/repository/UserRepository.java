package com.example.springsecurity.repository;

import com.example.springsecurity.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User,Integer>{
    User findUserById(int id);
}
