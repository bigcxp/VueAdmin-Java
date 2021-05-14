package com.cxp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cxp.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxp
 * @since 2021-05-14
 */
@Repository(value = "sysUserMapper")
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<Long> getNavMenuIds(Long userId);

	List<SysUser> listByMenuId(Long menuId);
}
