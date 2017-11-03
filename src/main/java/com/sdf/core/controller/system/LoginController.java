package com.sdf.core.controller.system;

import com.sdf.common.pojo.MSG;
import com.sdf.common.pojo.SessionUser;
import com.sdf.core.controller.BaseController;
import com.sdf.core.service.system.ISysFileUrlService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录
 *
 * @Date: 2017/10/21 16:08
 * @Author: SDF
 * @Version: 1.0
 */
@Scope("prototype")
@Controller
@RequestMapping("/login/")
public class LoginController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * hAdmin登录页
     * http://localhost/Frame/login/hAdmin
     *
     * @Date: 2017/10/21 16:09
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping(value = "hAdmin")
    public String page() {
        return BACK_PREFIX + "/system/login/hAdmin";
    }

    /**
     * 提交登录
     *
     * @Date: 2017/10/21 16:53
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping("submit")
    @ResponseBody
    public MSG submit(String code, String password, HttpSession session, HttpServletResponse response, HttpServletRequest request)
            throws Exception {
        MSG msg = new MSG();
        SessionUser sessionUserPO = sysUserService.login_submit(code, password, request);
        if (sessionUserPO != null) {
            if (sessionUserPO.getStatus() != 1) {
                return MSG.createErrorMSG("当前登录用户被限制，请联系管理员");
            }
            // 登录成功
            msg.setSuccess(true);
            // Cookie cookie = new Cookie("systemName",
            // sessionUserPO.getSystemName());
            // cookie.setMaxAge(60 * 60 * 24 * 30); // cookie 保存30天
            // cookie.setPath("/"); // 这个不能少
            // response.addCookie(cookie);

            // 设置左上角系统名称
            // SysContactUsPO contactUs = aboutService.getContactUs();
            // String name = "微信网红";
            // if (contactUs != null) {
            // name = contactUs.getCompanyName();
            // }
            // sessionUserPO.setSystemName(name);

            // 保存session对象
            session.setAttribute(BACK_SESSION_USER, sessionUserPO);
            session.setAttribute(BACK_SESSION_LOGIN_PAGE, sessionUserPO);
            // 设置跳转到系统功能界面
            List<Map<String, Object>> fMenus = sessionUserPO.getFmenus();
            if (fMenus.isEmpty()) {
//				msg.setInfo("/system/common/noRights.htm");
                msg.setInfo("/index/manage/page");
            } else {
                msg.setInfo(fMenus.get(0).get("menuUrl") + "");
            }
        } else {
            // 登录失败
            msg.setSuccess(false);
            msg.setInfo("用户名或密码不正确");
        }
        return msg;
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     * @throws Exception
     * @time 2016年10月9日 上午11:21:01
     */
    @RequestMapping("login_out")
    public ModelAndView ModelAndView(HttpSession session) throws Exception {
        session.removeAttribute(BACK_SESSION_USER);
        return new ModelAndView("redirect:/login");
    }

}
