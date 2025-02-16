package com.fl.fastLease.service.impl;

import com.fl.fastLease.mapper.TestMapper;
import com.fl.fastLease.model.Test;
import com.fl.fastLease.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * &#064;Description
 * &#064;Author Evan
 * &#064;Date 2025/2/16 1:22
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;


    @Override
    public List<Test> testSelect() {
        return testMapper.selectAll();
    }
}
