package com.sdf.core.service.sysMenu.impl;

import com.sdf.core.mapper.sysMenu.SysMenuDao;
import com.sdf.core.mapper.system.SysFileUrlDao;
import com.sdf.core.pojo.sysMenu.SysMenu;
import com.sdf.core.pojo.system.SysFileUrl;
import com.sdf.core.service.BaseService;
import com.sdf.core.service.sysMenu.ISysMenuService;
import com.sdf.core.service.system.ISysFileUrlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统菜单
 *
 * @Date: 2017/11/28 17:38
 * @Author: SDF
 * @Version: 1.0
 */
@Service
public class SysMenuServiceImpl extends BaseService implements ISysMenuService {

    @Resource
    private SysMenuDao sysMenuDao;

    @Override
    public List<SysMenu> selectAll() {
        return sysMenuDao.selectAll();
    }
}
