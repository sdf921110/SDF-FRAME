package com.sdf.core.controller.system;

import com.sdf.common.constant.SysConstant;
import com.sdf.common.exception.CustomRuntimeException;
import com.sdf.common.pojo.MSG;
import com.sdf.common.pojo.SessionUser;
import com.sdf.common.utils.StringUtil;
import com.sdf.core.controller.BaseController;
import com.sdf.core.service.system.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public String hAdmin() {
        return BACK_PREFIX + "/system/login/hAdmin";
    }

    /**
     * layuiCMS登录页
     * http://localhost/Frame/login/layuiCMS
     *
     * @Date: 2017/11/3 11:24
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping(value = "layuiCMS")
    public String layuiCMS() {
        return BACK_PREFIX + "/system/login/layuiCMS";
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
    public MSG submit(String code, String password, String loginUrl, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        MSG msg = new MSG();
        try {
            SessionUser sessionUser = sysUserService.login_submit(code, password, request);
            if (sessionUser != null) {
                if (sessionUser.getSysUser().getStatus() != 1) {
                    return MSG.createErrorMSG("当前登录用户被限制，请联系管理员");
                }
                // 登录成功
                msg.setSuccess(true);
                Cookie cookie = new Cookie("loginUrl", loginUrl);
                cookie.setMaxAge(60 * 60 * 24 * 30); // cookie 保存30天
                cookie.setPath("/"); // 这个不能少
                response.addCookie(cookie);

                // 设置左上角系统名称
                // SysContactUsPO contactUs = aboutService.getContactUs();
                // String name = "微信网红";
                // if (contactUs != null) {
                // name = contactUs.getCompanyName();
                // }
                // sessionUserPO.setSystemName(name);

                // 保存session对象
                session.setAttribute(BACK_SESSION_USER, sessionUser);
                session.setAttribute(BACK_SESSION_LOGIN_PAGE, loginUrl);
                // 设置跳转到系统功能界面
                List<Map<String, Object>> fMenus = sessionUser.getFmenus();
                if (fMenus.isEmpty()) {
//				msg.setInfo("/system/common/noRights.htm");
//              msg.setInfo("/index/manage/page");
                    msg.setInfo("/index/layuiCMS");
                } else {
                    msg.setInfo(fMenus.get(0).get("menuUrl") + "");
                }
            } else {
                // 登录失败
                msg.setSuccess(false);
                msg.setInfo("用户名或密码不正确");
            }

        } catch (CustomRuntimeException e) {
            // 登录失败
            msg.setSuccess(false);
            msg.setInfo(e.getMessage());
//            throw new CustomRuntimeException(e.getMessage(),e);
        }
        return msg;
    }

    /**
     * 解锁
     *
     * @Date: 2017/11/4 15:01
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping("unlocked")
    @ResponseBody
    public MSG unlocked(String password, HttpSession session)
            throws Exception {
        MSG msg = new MSG();
        SessionUser sessionUser = (SessionUser) session.getAttribute(BACK_SESSION_USER);
        if (sessionUser == null && sessionUser.getSysUser() == null) {
            return MSG.createErrorMSG(SysConstant.CODE_SYS_OUT_TIME, "超时请重新登录");
        }
        if (StringUtil.MD5Encode(password).equals(sessionUser.getSysUser().getPassword())) {
            return MSG.createSuccessMSG("解锁成功");
        }
        return MSG.createErrorMSG(SysConstant.CODE_ERROR_LOGIN_PASSWORD, "密码错误，请重试");
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     * @throws Exception
     * @time 2016年10月9日 上午11:21:01
     */
    @RequestMapping("out")
    public ModelAndView out(HttpSession session) throws Exception {
        session.removeAttribute(BACK_SESSION_USER);
        return new ModelAndView("redirect:" + session.getAttribute(BACK_SESSION_LOGIN_PAGE));
    }

}
