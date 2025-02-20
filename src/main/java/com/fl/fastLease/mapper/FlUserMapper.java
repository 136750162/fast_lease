package com.fl.fastLease.mapper;

import com.fl.fastLease.model.FlUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * &#064;Description UserMapper
 * &#064;Author Evan
 * &#064;Date 2025/2/17 21:54
 */
@Mapper
public interface FlUserMapper {

    /**
     * 查询用户信息， 根据OpenId
     * @param openId openId
     * @return 返回查询到的用户数据
     */
    FlUser getUser(String openId);


    /**
     * 新增或者更新用户数据
     * @param user 需要插入的用户数据
     * @return 返回操作结果
     */
    int insertOrUpdate(FlUser user);

    /**
     * 更新数据
     * @param user 用户对象
     * @return 返回操作结果
     */
    int update(FlUser user);
}
