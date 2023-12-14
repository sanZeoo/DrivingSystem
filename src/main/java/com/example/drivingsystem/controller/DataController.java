package com.example.drivingsystem.controller;

import com.example.drivingsystem.pojo.DataRecord;
import com.example.drivingsystem.response.ResponseResult;
import com.example.drivingsystem.services.IDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private IDataService dataService;
    /**
     *  上传数据
     * @param dataRecord
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult postData(@RequestBody DataRecord dataRecord){
        return dataService.postData(dataRecord);
    }

    @PostMapping("/upload/list")
    public ResponseResult postDataList(@RequestBody List<DataRecord> list){
        return dataService.postDataList(list);
    }

    /**
     *  获取司机数据
     * @param
     * @return
     */
    @GetMapping("/list")
    public ResponseResult list(@RequestParam("username") String useId){
        return dataService.list(useId);
    }

}
