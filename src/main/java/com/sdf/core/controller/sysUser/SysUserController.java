package com.sdf.core.controller.sysUser;

import com.sdf.common.constant.SysConstant;
import com.sdf.common.pojo.MSG;
import com.sdf.common.pojo.SessionUser;
import com.sdf.core.controller.BaseController;
import com.sdf.core.pojo.system.SysUser;
import com.sdf.core.service.system.ISysUserService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
    public String info(HttpSession session, Model model) {
        SysUser modelMap = getSysUser(session);
        model.addAttribute("modelMap", modelMap);
        return BACK_PREFIX + "/sysUser/info";
    }

    /**
     * 获取个人信息
     *
     * @Date: 2017/11/7 17:44
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping("getInfo")
    @ResponseBody
    public HashMap<String, Object> getInfo(HttpSession session)
            throws Exception {
        HashMap<String, Object> result = new HashMap<>();

        SysUser sysUser = getSysUser(session);

        result.put("result", sysUser);
        result.put("msg", MSG.createSuccessMSG());
        return result;
    }

    /**
     * 提交个人信息
     *
     * @Date: 2017/11/4 16:18
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping("submit")
    @ResponseBody
    public HashMap<String, Object> submit(SysUser sysUser, HttpSession session)
            throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        sysUserService.submit(result, sysUser, session);
        return result;
    }

    /**
     * 修改密码页面
     * http://localhost/Frame/sys-user/changePwd
     *
     * @Date: 2017/11/18 15:00
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping(value = "changePwd")
    public String changePwd(HttpSession session, Model model) {
        SysUser modelMap = getSysUser(session);
        model.addAttribute("modelMap", modelMap);
        return BACK_PREFIX + "/sysUser/changePwd";
    }

    /**
     * 提交密码修改
     *
     * @Date: 2017/11/18 17:51
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping("changePwdSubmit")
    @ResponseBody
    public HashMap<String, Object> changePwdSubmit(HashMap<String, Object> result, String oldPwd, SysUser sysUser, HttpSession session)
            throws Exception {
        sysUserService.changePwdSubmit(result, oldPwd, sysUser, session);
        return result;
    }


}
