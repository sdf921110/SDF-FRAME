package com.sdf.core.controller.sysMenu;

import com.sdf.common.pojo.MSG;
import com.sdf.core.controller.BaseController;
import com.sdf.core.pojo.sysMenu.SysMenu;
import com.sdf.core.pojo.system.SysUser;
import com.sdf.core.service.sysMenu.ISysMenuService;
import com.sdf.core.service.system.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * 系统菜单
 *
 * @Date: 2017/11/28 15:47
 * @Author: SDF
 * @Version: 1.0
 */
@Scope("prototype")
@Controller
@RequestMapping("/sys-menu/")
public class SysMenuController extends BaseController {

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 系统菜单页面
     * http://localhost/Frame/sys-menu/page
     *
     * @Date: 2017/11/4 16:18
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping(value = "page")
    public String info(HttpSession session, Model model) {
        SysUser modelMap = getSysUser(session);
        model.addAttribute("modelMap", modelMap);
        return BACK_PREFIX + "/sysMenu/list";
    }

    /**
     * 获取所有系统菜单
     *
     * @Date: 2017/11/7 17:44
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping("selectAll")
    @ResponseBody
    public HashMap<String, Object> selectAll(HttpSession session)
            throws Exception {
        HashMap<String, Object> result = new HashMap<>();

        List<SysMenu> sysMenus = sysMenuService.selectAll();

        result.put("result", sysMenus);
        result.put("msg", MSG.createSuccessMSG());
        return result;
    }


}
