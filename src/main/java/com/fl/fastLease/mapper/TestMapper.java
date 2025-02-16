package com.fl.fastLease.mapper;

import com.fl.fastLease.model.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * &#064;Description
 * &#064;Author Evan
 * &#064;Date 2025/2/16 1:12
 */
@Mapper
public interface TestMapper {

    List<Test> selectAll();
}
