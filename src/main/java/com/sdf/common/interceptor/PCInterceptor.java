package com.sdf.common.interceptor;

import com.sdf.common.pojo.SessionUser;
import com.sdf.core.controller.BaseController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 系统过滤器
 *
 * @author SDF
 * @date 2016年11月9日
 */
public class PCInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("=======================>>请求 url   " + url);
        if (url.indexOf("upload") != -1 || url.indexOf("api") != -1 || url.indexOf("demo") != -1
                || url.indexOf("login") != -1 || url.indexOf("index") != -1) {
            return true;
        }

        SessionUser obj = (SessionUser) request.getSession().getAttribute(BaseController.BACK_SESSION_USER);

        if (obj == null) {
            // String systemName = "";
            // Cookie[] cookies = request.getCookies();
            // if (cookies != null) {
            // for (int i = 0; i < cookies.length; i++) {
            // if (cookies[i].getName().equals("systemName")) {
            // systemName = cookies[i].getValue();
            // }
            // }
            // }
            // System.err.println("systemName="+systemName);
            PrintWriter out = response.getWriter();
            StringBuilder sb = new StringBuilder();
            sb.append("<script type='text/javascript' charset='utf-8'>");
            // sb.append("alert('提示：页面过期，请重新登录.');");
            sb.append("window.location.href='" + request.getContextPath() + "/login';");
            sb.append("</script>");
            out.print(sb.toString());
            out.close();
            return false;
        }
        String menuCode = request.getParameter("menuCode");
        if (menuCode != null) {
            obj.setCurrenMenuCode(menuCode + "");
            request.getSession().setAttribute(BaseController.BACK_SESSION_USER, obj);
        }
        return super.preHandle(request, response, handler);
    }

}
