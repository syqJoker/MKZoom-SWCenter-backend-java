package com.mksun.swcenter.dao;

import com.mksun.swcenter.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TestDao {
    List<Test> queryTestList();
    Test queryTestById(String id);
}
