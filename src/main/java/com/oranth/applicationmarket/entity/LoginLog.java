package com.oranth.applicationmarket.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理员操作记录表
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    /**
     * 事件记录
     */
    private String eventRecord;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 登入IP
     */
    private String loginIp;


}
