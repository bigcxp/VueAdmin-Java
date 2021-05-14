package com.cxp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxp.entity.SysRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxp
 * @since 2021-05-14
 */
public interface SysRoleService extends IService<SysRole> {
    List<SysRole> listRolesByUserId(Long userId);
}
