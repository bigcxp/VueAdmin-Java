package com.cxp.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxp.common.lang.Const;
import com.cxp.common.lang.HttpResult;
import com.cxp.entity.SysRole;
import com.cxp.entity.SysRoleMenu;
import com.cxp.service.SysRoleMenuService;
import com.cxp.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cxp
 * @since 2021-05-14
 */
@RestController
@RequestMapping("/sys-role")
public class SysRoleController extends BaseController {


}
