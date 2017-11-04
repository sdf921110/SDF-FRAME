package com.sdf.core.controller.sysUser;

import com.sdf.common.constant.SysConstant;
import com.sdf.common.pojo.MSG;
import com.sdf.common.pojo.SessionUser;
import com.sdf.core.controller.BaseController;
import com.sdf.core.pojo.system.SysUser;
import com.sdf.core.service.system.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 系统后台用户
 *
 * @Date: 2017/11/4 16:16
 * @Author: SDF
 * @Version: 1.0
 */
@Scope("prototype")
@Controller
@RequestMapping("/sys-user/")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 后台用户信息页面
     * http://localhost/Frame/sys-user/info
     *
     * @Date: 2017/11/4 16:18
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping(value = "info")
    public String info() {
        return BACK_PREFIX + "/sysUser/info";
    }

    /**
     * 编辑个人信息
     *
     * @Date: 2017/11/4 16:18
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping("submit")
    @ResponseBody
    public MSG submit(SysUser sysUser, HttpSession session)
            throws Exception {
        MSG msg = new MSG();
        sysUserService.submit(sysUser,session);





        return msg;
    }


}
