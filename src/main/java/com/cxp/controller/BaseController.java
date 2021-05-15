package com.cxp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxp.service.*;
import com.cxp.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: cxp
 * @Date: 2021/5/14
 * @Time: 12:47
 * @Description: 公共控制类
 */
public class BaseController {
    @Autowired
    HttpServletRequest req;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    /**
     * 获取页面
     * @return
     */
    public Page getPage() {
        int current = ServletRequestUtils.getIntParameter(req, "cuurent", 1);
        int size = ServletRequestUtils.getIntParameter(req, "size", 10);

        return new Page(current, size);
    }
}
