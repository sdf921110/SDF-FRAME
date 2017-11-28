package com.sdf.core.service.sysMenu;

import com.sdf.core.pojo.sysMenu.SysMenu;
import com.sdf.core.pojo.system.SysFileUrl;

import java.util.List;

/**
 * 系统菜单
 *
 * @Date: 2017/11/28 17:38
 * @Author: SDF
 * @Version: 1.0
 */
public interface ISysMenuService {

    public List<SysMenu> selectAll();

}
