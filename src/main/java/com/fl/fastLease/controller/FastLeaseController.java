package com.fl.fastLease.controller;

import com.fl.fastLease.model.Test;
import com.fl.fastLease.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * &#064;Description
 * &#064;Author Evan
 * &#064;Date 2025/2/16 1:08
 */
@RestController
@RequestMapping("/fast/lease")
public class FastLeaseController {

    @Autowired
    private TestService testService;


    @GetMapping("/test")
    public List<Test> test(){
        return testService.testSelect();
    }
}
