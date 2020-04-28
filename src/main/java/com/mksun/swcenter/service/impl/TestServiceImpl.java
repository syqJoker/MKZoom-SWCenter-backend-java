package com.mksun.swcenter.service.impl;

import com.mksun.swcenter.dao.TestDao;
import com.mksun.swcenter.entity.Test;
import com.mksun.swcenter.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TestService")
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    @Override
    public List<Test> queryTestList() {
        return testDao.queryTestList();
    }

    @Override
    public Test queryTestById(String id) {
        return testDao.queryTestById(id);
    }

    @Override
    public int registerTest(Test test) {
        return testDao.saveTest(test);
    }
}
