package com.fl.fastLease.controller;

import com.fl.fastLease.common.entity.ResultEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * &#064;Description
 * &#064;Author Evan
 * &#064;Date 2025/2/18 21:21
 */
@RestController
@RequestMapping("/business")
public class UserController {


    @GetMapping("/onLogin")
    public ResultEntity<Object> onLogin(String code){

        return null;
    }
}
