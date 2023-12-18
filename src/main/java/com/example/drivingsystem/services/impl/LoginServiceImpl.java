package com.example.drivingsystem.services.impl;

import com.example.drivingsystem.dao.UserDao;
import com.example.drivingsystem.pojo.User;
import com.example.drivingsystem.response.ResponseResult;
import com.example.drivingsystem.services.ILoginService;
import com.example.drivingsystem.utils.SnowflakeIdWorker;
import com.example.drivingsystem.utils.TextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private SnowflakeIdWorker idWorker;

    @Autowired
    private UserDao userDao;


    @Override
    public ResponseResult register(User user) {


        if (TextUtils.isEmpty(user.getUsername())) {
            return ResponseResult.FAILED("用户不能为空");
        }
        User userFormDb = userDao.findOneByUsername(user.getUsername());
        if (userFormDb!=null){
            return ResponseResult.FAILED("该用户名已注册.");
        }
        if (TextUtils.isEmpty(user.getPassword())) {
            return ResponseResult.FAILED("密码不能为空");
        }
        if (TextUtils.isEmpty(user.getType())) {
            return ResponseResult.FAILED("类型不能为空");
        }

        user.setId(String.valueOf(idWorker.nextId()));
        userDao.save(user);
        return ResponseResult.SUCCESS("注册成功");
    }

    @Override
    public ResponseResult login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (TextUtils.isEmpty(username)) {
            return ResponseResult.FAILED("账号不可以为空.");
        }
        if (TextUtils.isEmpty(password)) {
            return ResponseResult.FAILED("密码不能为空");
        }

        User userFromDb = userDao.findOneByUsername(user.getUsername());

        if (userFromDb == null) {
            return ResponseResult.FAILED("用户名或密码不正确");
        }

        boolean matches = password.equals(userFromDb.getPassword());
        if (!matches) {
            return ResponseResult.FAILED("用户名或密码不正确");
        }

        return ResponseResult.SUCCESS("登录成功");
    }

}
