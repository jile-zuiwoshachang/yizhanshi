package com.yizhanshi.common.core.exception.auth;

import org.apache.commons.lang3.StringUtils;

/**
 * 未能通过的角色认证异常
 * 
 * @author hejiale
 */
public class NotRoleException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public NotRoleException(String role)
    {
        super(role);
    }

    /**
     * 用于将数组roles中的元素使用逗号（,）连接成一个单一的字符串。每个角色名之间由逗号分隔。
     * 如果roles数组是["role1", "role2", "role3"]，那么StringUtils.join方法的结果将是"role1,role2,role3"
     * 创建一个异常实例，这个实例包含了一个描述了哪些角色导致异常的消息
     * @param roles
     */
    public NotRoleException(String[] roles)
    {
        super(StringUtils.join(roles, ","));
    }
}
