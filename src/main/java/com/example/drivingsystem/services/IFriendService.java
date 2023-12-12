package com.example.drivingsystem.services;

import com.example.drivingsystem.pojo.Friend;
import com.example.drivingsystem.response.ResponseResult;

public interface IFriendService {

    ResponseResult bindFriend(Friend friend);

    ResponseResult list(Friend friend);

    ResponseResult updateStatus(Friend friend);

}
