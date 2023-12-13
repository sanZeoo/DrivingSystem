package com.example.drivingsystem.services;

import com.example.drivingsystem.pojo.Friend;
import com.example.drivingsystem.response.ResponseResult;

public interface IFriendService {

    ResponseResult bindFriend(String username,String friendId);

    ResponseResult list(String friendId);

    ResponseResult updateStatus(Friend friend);

}
