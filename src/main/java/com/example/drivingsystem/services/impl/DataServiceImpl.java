package com.example.drivingsystem.services.impl;

import com.example.drivingsystem.dao.DataDao;
import com.example.drivingsystem.pojo.DataRecord;
import com.example.drivingsystem.response.ResponseResult;
import com.example.drivingsystem.services.IDataService;
import com.example.drivingsystem.utils.SnowflakeIdWorker;
import com.example.drivingsystem.utils.TextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Slf4j
@Service
@Transactional
public class DataServiceImpl implements IDataService {

    @Autowired
    private SnowflakeIdWorker idWorker;

    @Autowired
    private DataDao dataDao;
    @Override
    public ResponseResult postData(DataRecord dataRecord) {
        String useId = dataRecord.getUseId();
        String type = dataRecord.getType();
        String dateTime = dataRecord.getDateTime();
        if (TextUtils.isEmpty(useId)) {
            return ResponseResult.FAILED("司机用户不能为空");
        }
        if (TextUtils.isEmpty(type)) {
            return ResponseResult.FAILED("疲劳类型不能为空");
        }
        if (TextUtils.isEmpty(dateTime)) {
            return ResponseResult.FAILED("疲劳时间不能为空");
        }
        DataRecord recordFormDb = dataDao.findOneByUseIdAndDateTime(useId, dateTime);

        if (recordFormDb !=null){
            return ResponseResult.FAILED("数据已经插入成功");
        }
        dataRecord.setId(String.valueOf(idWorker.nextId()));
        dataDao.save(dataRecord);
        return ResponseResult.SUCCESS("数据插入成功");
    }

    @Override
    public ResponseResult list(String useId) {
        if (TextUtils.isEmpty(useId)) {
            return ResponseResult.FAILED("司机用户不能为空");
        }
        List<DataRecord> dataRecords = dataDao.findAllByUseId(useId);

        if (dataRecords==null){
            return ResponseResult.SUCCESS().setData(null);
        }
        // 状态为 请求状态  接收人去搜数据库 ---返回 多少条+用户
        // 状态为 好友状态  --- 亲友列表

        return ResponseResult.SUCCESS().setData(dataRecords);
    }
}
