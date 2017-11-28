package com.sdf.core.mapper.sysMenu;

import com.sdf.core.pojo.sysMenu.SysMenu;
import com.sdf.core.pojo.system.SysFileUrl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统菜单
 *
 * @Date: 2017/11/28 17:38
 * @Author: SDF
 * @Version: 1.0
 */
@Repository
public interface SysMenuDao {

    List<SysMenu> selectAll();
}
