package com.cxp.controller;

import com.cxp.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cxp
 * @Date: 2021/5/14
 * @Time: 13:18
 * @Description: TODO
 */
@RestController
public class TestController {
    @Autowired
    SysUserService userService;

    @GetMapping("test")
    public Object test() {
        return userService.list();
    }
}
