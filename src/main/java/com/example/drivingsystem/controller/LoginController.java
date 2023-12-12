package com.example.drivingsystem.controller;

import com.example.drivingsystem.pojo.User;
import com.example.drivingsystem.response.ResponseResult;
import com.example.drivingsystem.services.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class LoginController {


    @Autowired
    private ILoginService loginService;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
        return loginService.register(user);
    }

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }



}
