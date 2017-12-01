package com.sdf.core.service.sysMenu.impl;

import com.sdf.core.mapper.sysMenu.SysMenuDao;
import com.sdf.core.pojo.sysMenu.SysMenu;
import com.sdf.core.service.BaseService;
import com.sdf.core.service.sysMenu.ISysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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

/*    [{
        "title" : "后台首页",
                "icon" : "icon-computer",
                "href" : "page/main.html",
                "spread" : false
    },{
        "title" : "其他页面",
                "icon" : "&#xe630;",
                "href" : "",
                "spread" : false,
                "children" : [
        {
            "title" : "404页面",
                "icon" : "&#xe61c;",
                "href" : "page/404.html",
                "spread" : false
        }]
    }]*/

    @Override
    public List<SysMenu> selectAll() {
        List<SysMenu> sysMenus = sysMenuDao.selectAll();

        List<SysMenu> firstList = new ArrayList<>();
        List<SysMenu> secondList = new ArrayList<>();

//        HashMap<String,HashMap<String,List<SysMenu>>> result = new HashMap<>();
//        HashMap<String,List<SysMenu>> first = new HashMap<>();
//        HashMap<String,List<SysMenu>> second = new HashMap<>();

        for (SysMenu sysMenu : sysMenus){
            Integer level = sysMenu.getLevel();
//            boolean spread = sysMenu.getSpread()==1?true:false;

            // 二级菜单
            if (level==2){
                secondList.add(sysMenu);
            }
            // 一级菜单
            if (level==1){
                firstList.add(sysMenu);
            }
        }

        for (SysMenu sysMenu1 : firstList){
            for (SysMenu sysMenu2 : secondList){
                List<SysMenu> childrenList = new ArrayList<>();
                // 找到二级菜单对应的一级菜单
                if (sysMenu2.getPid().equals(sysMenu1.getId())){
                    childrenList.add(sysMenu2);
                }
                sysMenu1.setChildren(childrenList);
            }
        }

        return firstList;
    }
}
