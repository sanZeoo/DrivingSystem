package com.example.drivingsystem.dao;

import com.example.drivingsystem.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,String> , JpaSpecificationExecutor<User> {

    User findOneByUsername(String username);
}
