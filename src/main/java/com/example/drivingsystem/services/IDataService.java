package com.example.drivingsystem.services;

import com.example.drivingsystem.pojo.DataRecord;
import com.example.drivingsystem.response.ResponseResult;

import java.util.List;

public interface IDataService {
    ResponseResult postData(DataRecord dataRecord);

    ResponseResult postDataList(List<DataRecord> list);

    ResponseResult list(String useId);
}
