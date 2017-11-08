package com.sdf.core.controller;

import com.sdf.common.pojo.SessionUser;
import com.sdf.core.pojo.system.SysUser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;

//import org.apache.log4j.Logger;

/**
 * BaseController
 *
 * @author SDF
 * @date 2016年9月8日
 */
public class BaseController {

    public final String BACK_PREFIX = "back";
    public final String FRONT_PREFIX = "front";

    public static final String BACK_SESSION_USER = "back_session_user";
    // 后台登录页
    public static final String BACK_SESSION_LOGIN_PAGE = "back_session_login_page";
    public static final String FRONT_SESSION_USER = "front_session_user";

    protected String datafomat = "yyyy-MM-dd";

    // 获取日志记录器（使用slf4j）
    // protected Logger logger = LoggerFactory.getLogger(getClass());
    // protected Logger logger = Logger.getLogger(getClass());
    protected static Logger logger = Logger.getLogger(BaseController.class);

    /**
     * 获取session信息
     *
     * @param session
     * @return Object
     * @time 2016年11月22日 上午10:00:59
     */
    protected Object getSesseion(HttpSession session) {
        if (session.getAttribute(BACK_SESSION_USER) == null) {
            return null;
        }
        return session.getAttribute(BACK_SESSION_USER);
    }

    /**
     * 获取session信息中登录对象
     *
     * @param session
     * @return SessionUser
     * @time 2016年11月22日 上午10:01:31
     */
    protected SessionUser getSesseionUser(HttpSession session) {
        if (session.getAttribute(BACK_SESSION_USER) == null) {
            return null;
        }
        return (SessionUser) session.getAttribute(BACK_SESSION_USER);
    }

    /**
     * 获取操作人对象
     *
     * @param session
     * @return
     * @time 2016年11月22日 上午10:00:59
     */
    protected SysUser getSysUser(HttpSession session) {
        if (session.getAttribute(BACK_SESSION_USER) == null) {
            return null;
        }
        SessionUser SessionUser = (SessionUser) session.getAttribute(BACK_SESSION_USER);
        return SessionUser.getSysUser();
    }

    /**
     * 获取操作人
     *
     * @param session
     * @return
     * @time 2016年11月22日 上午10:00:59
     */
    protected String getSesseionUserName(HttpSession session) {
        SysUser sysUser = getSysUser(session);
        return sysUser.getName();
    }

    /**
     * 获取操作人ID
     *
     * @param session
     * @return
     * @time 2017年3月24日 下午3:33:10
     */
    protected Integer getSesseionUserId(HttpSession session) {
        SysUser sysUser = getSysUser(session);
        return sysUser.getId();
    }

    // @SuppressWarnings("unused")
    // private void log4j() {
    // // 记录debug级别的信息
    // logger.debug("This is debug message.");
    // // 记录info级别的信息
    // logger.info("This is info message.");
    // // 记录warn级别的信息
    // logger.warn("This is warn message.");
    // // 记录error级别的信息
    // logger.error("This is error message.");
    // }

    /**
     * 将发送请求页面中form表单里所有具有name属性的表单对象获取(包括button).返回一个HashMap
     *
     * @param request
     * @return
     */
    public static HashMap<String, String> constructParameter(HttpServletRequest request) {
        // 将发送请求页面中form表单里所有具有name属性的表单对象获取(包括button).返回一个Enumeration类型的枚举.
        Enumeration<String> parameterMap = request.getParameterNames();
        HashMap<String, String> map = new HashMap<>();

        while (parameterMap.hasMoreElements()) {
            String p = (String) parameterMap.nextElement();
            map.put(p, request.getParameter(p));
        }

        return map;
    }

}
