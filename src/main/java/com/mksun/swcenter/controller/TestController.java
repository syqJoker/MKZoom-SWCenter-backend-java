package com.mksun.swcenter.controller;

import com.mksun.swcenter.commonService.RedisService;
import com.mksun.swcenter.entity.RtnJSON;
import com.mksun.swcenter.entity.Test;
import com.mksun.swcenter.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private RedisService redisService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/queryTests")
    public RtnJSON queryTests(@RequestParam(value = "id", defaultValue = "") String id) {
        RtnJSON result = new RtnJSON();
        result.setInfo(testService.queryTestList());
        return result;
    }

    @GetMapping("/queryTestById")
    public RtnJSON queryTestById(@RequestParam(value = "id", defaultValue = "") String id) {
        RtnJSON result = new RtnJSON();
        Test target = testService.queryTestById(id);
        if(target != null && target.getId() != null && !"".equalsIgnoreCase(target.getId())){
            Map<String,Object> resultInfo = new HashMap<>();
            String redisResult = redisService.get(target.getId());
            if(redisResult != null && !"".equalsIgnoreCase(redisResult)){
                resultInfo.put("target",target);
                resultInfo.put("redis",redisResult);
                result.setInfo(resultInfo);
                return result;
            }else{
                return result.dataError("未找到redis中数据");
            }
        }else{
            return result.dataError("未找到数据");
        }
    }


    @PostMapping("/registerTest")
    public RtnJSON registerTest(Test test) {
        RtnJSON result = new RtnJSON();
        if(test.getCreateId() == null || "".equalsIgnoreCase(test.getCreateId())){
            test.setCreateId(test.getId());
        }
        if(test.getCreateTime() == null){
            test.setCreateTime(new Date());
        }
        int saveResult = testService.registerTest(test);
        if(saveResult>0){
            redisService.set(test.getId(),test.toString());
        }
        return result;
    }
}
