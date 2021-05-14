package com.cxp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxp.common.dto.SysMenuDto;
import com.cxp.entity.SysMenu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxp
 * @since 2021-05-14
 */
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenuDto> getCurrentUserNav();

	List<SysMenu> tree();

}
