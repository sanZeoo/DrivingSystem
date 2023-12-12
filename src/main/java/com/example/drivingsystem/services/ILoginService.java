package com.example.drivingsystem.services;

import com.example.drivingsystem.pojo.User;
import com.example.drivingsystem.response.ResponseResult;

public interface ILoginService {
    ResponseResult register(User user);

    ResponseResult login(User user);
}
