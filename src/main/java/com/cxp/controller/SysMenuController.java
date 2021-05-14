package com.cxp.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxp.common.dto.SysMenuDto;
import com.cxp.common.lang.HttpResult;
import com.cxp.entity.SysMenu;
import com.cxp.entity.SysRoleMenu;
import com.cxp.entity.SysUser;
import com.cxp.service.SysMenuService;
import com.cxp.service.SysRoleMenuService;
import com.cxp.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxp
 * @since 2021-05-14
 */
@RestController
@RequestMapping("/sys-menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;
     @Autowired
    private SysRoleMenuService sysRoleMenuService;
    /**
	 * 用户当前用户的菜单和权限信息
	 * @param principal
	 * @return
	 */
	@GetMapping("/nav")
	public HttpResult nav(Principal principal) {
		SysUser sysUser = sysUserService.getByUsername(principal.getName());

		// 获取权限信息
		String authorityInfo = sysUserService.getUserAuthorityInfo(sysUser.getId());// ROLE_admin,ROLE_normal,sys:user:list,....
		String[] authorityInfoArray = StringUtils.tokenizeToStringArray(authorityInfo, ",");

		// 获取导航栏信息
		List<SysMenuDto> navs = sysMenuService.getCurrentUserNav();

		return HttpResult.succ(MapUtil.builder()
				.put("authoritys", authorityInfoArray)
				.put("nav", navs)
				.map()
		);
	}

	@GetMapping("/info/{id}")
	@PreAuthorize("hasAuthority('sys:menu:list')")
	public HttpResult info(@PathVariable(name = "id") Long id) {
		return HttpResult.succ(sysMenuService.getById(id));
	}

	@GetMapping("/list")
	@PreAuthorize("hasAuthority('sys:menu:list')")
	public HttpResult list() {

		List<SysMenu> menus = sysMenuService.tree();
		return HttpResult.succ(menus);
	}

	@PostMapping("/save")
	@PreAuthorize("hasAuthority('sys:menu:save')")
	public HttpResult save(@Validated @RequestBody SysMenu sysMenu) {

		sysMenu.setCreated(LocalDateTime.now());

		sysMenuService.save(sysMenu);
		return HttpResult.succ(sysMenu);
	}

	@PostMapping("/update")
	@PreAuthorize("hasAuthority('sys:menu:update')")
	public HttpResult update(@Validated @RequestBody SysMenu sysMenu) {

		sysMenu.setUpdated(LocalDateTime.now());

		sysMenuService.updateById(sysMenu);

		// 清除所有与该菜单相关的权限缓存
		sysUserService.clearUserAuthorityInfoByMenuId(sysMenu.getId());
		return HttpResult.succ(sysMenu);
	}

	@PostMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('sys:menu:delete')")
	public HttpResult delete(@PathVariable("id") Long id) {

		int count = sysMenuService.count(new QueryWrapper<SysMenu>().eq("parent_id", id));
		if (count > 0) {
			return HttpResult.fail("请先删除子菜单");
		}

		// 清除所有与该菜单相关的权限缓存
		sysUserService.clearUserAuthorityInfoByMenuId(id);

		sysMenuService.removeById(id);

		// 同步删除中间关联表
		sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq("menu_id", id));
		return HttpResult.succ("");
	}

}
