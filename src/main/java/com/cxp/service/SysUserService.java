package com.cxp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxp.entity.SysUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cxp
 * @since 2021-05-14
 */
public interface SysUserService extends IService<SysUser> {
    SysUser getByUsername(String username);

    String getUserAuthorityInfo(Long userId);

    void clearUserAuthorityInfo(String username);

    void clearUserAuthorityInfoByRoleId(Long roleId);

    void clearUserAuthorityInfoByMenuId(Long menuId);

}
