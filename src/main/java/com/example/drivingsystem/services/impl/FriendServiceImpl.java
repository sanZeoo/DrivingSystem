package com.example.drivingsystem.services.impl;

import com.example.drivingsystem.dao.FriendDao;
import com.example.drivingsystem.pojo.Friend;
import com.example.drivingsystem.response.ResponseResult;
import com.example.drivingsystem.services.IFriendService;
import com.example.drivingsystem.utils.SnowflakeIdWorker;
import com.example.drivingsystem.utils.TextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
@Transactional
public class FriendServiceImpl implements IFriendService {

    @Autowired
    private SnowflakeIdWorker idWorker;

    @Autowired
    private FriendDao friendDao;
    @Override
    public ResponseResult bindFriend(Friend friend) {
        // 不为空
        String useId = friend.getUseId();
        String friendId = friend.getFriendId();
        if (TextUtils.isEmpty(useId)) {
            return ResponseResult.FAILED("请求人不能为空");
        }
        if (TextUtils.isEmpty(friendId)) {
            return ResponseResult.FAILED("接受人不能为空");
        }
        Friend friendFormDb = friendDao.findOneByUseIdAndFriendId(useId, friendId);
        if (friendFormDb!=null){
            return ResponseResult.FAILED("你已发送了信息,请等待对方确认");
        }
        // 查数据是否有好友
        friend.setId(String.valueOf(idWorker.nextId()));
        friend.setStatus("0");
        Date date = new Date();
        friend.setCreatedAt(date);
        friend.setUpdatedAt(date);

        friendDao.save(friend);
        // 发起请求 改变状态
        return ResponseResult.SUCCESS("正在请求绑定亲友...");
    }

    /**
     * 返回 所有列表 等 客户端自己处理
     *
     * @param friend
     * @param type
     * @return
     */
    @Override
    public ResponseResult list(Friend friend) {
        // 不为空

        // 确认人 可以 看有多少信息。 和 好友列表
        String friendId = friend.getFriendId();
        if (TextUtils.isEmpty(friendId)) {
            return ResponseResult.FAILED("接受人不能为空");
        }
        List<Friend> friends = friendDao.findAllByFriendId(friendId);
        if (friends==null){
            return ResponseResult.SUCCESS().setData(null);
        }
        // 状态为 请求状态  接收人去搜数据库 ---返回 多少条+用户
        // 状态为 好友状态  --- 亲友列表

        return ResponseResult.SUCCESS().setData(friends);
    }

    /**
     *  更新 好友状态
     * @param friend
     * @return
     */
    @Override
    public ResponseResult updateStatus(Friend friend) {
        String useId = friend.getUseId();
        String friendId = friend.getFriendId();
        String status = friend.getStatus();
        if (TextUtils.isEmpty(useId)) {
            return ResponseResult.FAILED("请求人不能为空");
        }
        if (TextUtils.isEmpty(friendId)) {
            return ResponseResult.FAILED("接受人不能为空");
        }
        if (TextUtils.isEmpty(status)) {
            return ResponseResult.FAILED("状态不能为空");
        }
        Friend friendFormDb = friendDao.findOneByUseIdAndFriendId(useId, friendId);
        friendFormDb.setStatus(status);
        friendFormDb.setUpdatedAt(new Date());
        friendDao.save(friendFormDb);
        // 如果是添加好友 在数据加上另一条信息
        if (status.equals("1")) {
            Friend anOther = friendDao.findOneByUseIdAndFriendId(friendId, useId);
            if (anOther!=null){
                anOther.setStatus(status);
                friendDao.save(anOther);
            }else {
                Friend anotherRecord = new Friend();
                anotherRecord.setId(String.valueOf(idWorker.nextId()));
                anotherRecord.setUseId(friendId);
                anotherRecord.setFriendId(useId);
                anotherRecord.setUpdatedAt(new Date());
                anotherRecord.setCreatedAt(new Date());
                anotherRecord.setStatus(status);
                friendDao.save(anotherRecord);
            }
        }

        return ResponseResult.SUCCESS("修改成功");

    }


}
