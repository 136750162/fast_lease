package com.fl.fastLease.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * &#064;Description
 * &#064;Author Evan
 * &#064;Date 2025/2/16 1:09
 */
@Data
public class Test {

    private Long id;

    private Integer userId;

    private String ip;

    private String conversationId;

    private Long firstChatMessageId;

    private String firstMessageId;

    private String title;

    private String apiType;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
