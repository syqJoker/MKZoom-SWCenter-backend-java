package com.mksun.swcenter.service;

import com.mksun.swcenter.entity.Test;

import java.util.List;

public interface TestService {
    public List<Test> queryTestList();
    public Test queryTestById(String id);
}
