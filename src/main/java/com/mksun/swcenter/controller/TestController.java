package com.mksun.swcenter.controller;

import com.mksun.swcenter.entity.RtnJSON;
import com.mksun.swcenter.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

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
        result.setInfo(testService.queryTestById(id));
        return result;
    }
}
