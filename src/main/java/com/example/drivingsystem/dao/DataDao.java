package com.example.drivingsystem.dao;

import com.example.drivingsystem.pojo.DataRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DataDao extends JpaRepository<DataRecord,String>, JpaSpecificationExecutor<DataRecord> {

    DataRecord findOneByUseIdAndDateTime(String useId, String dateTime);

    List<DataRecord> findAllByUseId(String userId);

}
