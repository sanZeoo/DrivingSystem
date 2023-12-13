package com.example.drivingsystem.controller;

import com.example.drivingsystem.pojo.Friend;
import com.example.drivingsystem.response.ResponseResult;
import com.example.drivingsystem.services.IFriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private IFriendService friendService;

    /**
     * 绑定亲友
     * @return
     */
    @GetMapping("/bind")
    public ResponseResult bindFriend(@RequestParam("username")String useId,@RequestParam("friendId")String friendId){

        return friendService.bindFriend(useId,friendId);
    }

    /**
     * 获取添加信息 和 亲友列表
     * @return
     */
    @GetMapping("/list")
    public ResponseResult list(@RequestParam("friendId")String friendId){
        return friendService.list(friendId);
    }


    @PostMapping("/update")
    public ResponseResult updateStatus(@RequestBody Friend friend){
        return friendService.updateStatus(friend);
    }

}
