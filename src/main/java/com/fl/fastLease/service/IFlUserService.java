package com.fl.fastLease.service;

import com.fl.fastLease.common.entity.ResultEntity;
import com.fl.fastLease.model.FlUser;
import com.fl.fastLease.request.WechatUserEncryptedRequest;
import com.fl.fastLease.vo.FlUserVO;

/**
 * &#064;Description
 * &#064;Author Evan
 * &#064;Date 2025/2/17 22:30
 */
public interface IFlUserService {

    /**
     * 微信用户登录
     * @param code 用户授权code
     */
    ResultEntity<FlUserVO> onLogin(String code);

    /**
     * 检查用户是否登录成功
     * @param sessionKey 令牌
     * @return 如果用户已登录则返回数据，否则返回空
     */
    ResultEntity<FlUserVO> checkLogin(String sessionKey);

    /**
     * 解密微信用户关键数据，并且落库
     * @return 返回操作结果
     */
    ResultEntity<Object> decryptUserData(WechatUserEncryptedRequest request);


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
}
