package com.example.drivingsystem.dao;

import com.example.drivingsystem.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FriendDao extends JpaRepository<Friend,String>, JpaSpecificationExecutor<Friend> {

    Friend findOneByUseIdAndFriendId(String userId,String friendId);

    List<Friend> findAllByFriendId(String friendId);
    List<Friend> findAllByUseId(String useId);
}
