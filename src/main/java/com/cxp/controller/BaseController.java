package com.cxp.controller;

import org.springframework.beans.factory.annotation.Autowired;

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
}
