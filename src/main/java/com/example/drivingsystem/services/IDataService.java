package com.example.drivingsystem.services;

import com.example.drivingsystem.pojo.DataRecord;
import com.example.drivingsystem.response.ResponseResult;

public interface IDataService {
    ResponseResult postData(DataRecord dataRecord);

    ResponseResult list(String useId);
}
