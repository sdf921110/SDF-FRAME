package com.sdf.common.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdf.common.anno.UserToken;
import com.sdf.common.enu.TokenType;
import com.sdf.common.pojo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MobileInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;

            Class<?> clazz = hm.getBeanType();
            Method m = hm.getMethod();


            try {
                if (clazz != null && m != null) {
                    boolean isClzAnnotation = clazz.isAnnotationPresent(UserToken.class);
                    boolean isMethondAnnotation = m.isAnnotationPresent(UserToken.class);
                    UserToken userToken = null;
                    // 如果方法和类声明中同时存在这个注解，那么方法中的会覆盖类中的设定。
                    if (isMethondAnnotation) {
                        userToken = m.getAnnotation(UserToken.class);
                    } else if (isClzAnnotation) {
                        userToken = clazz.getAnnotation(UserToken.class);
                    }
                    if (userToken != null) {
                        if (TokenType.NoValidate == userToken.value()) {
                            // 标记为不验证,放行
                            return true;
                        } else {

                            String userId = request.getParameter("userId");
                            String usertoken = request.getParameter("user_token");
                            String fromHfive = request.getParameter("fromHFive");
                            if (fromHfive != null && "true".equals(fromHfive)) {
                                return true;
                            }
                            if (StringUtils.isBlank(userId) ||
                                    StringUtils.isBlank(usertoken)) {
                                String infoString = "userId或user_token参数缺失!";
                                int code = 0;
                                toJson(response, infoString, code);
                                return false;
                            }

//                            if (!userService.userOnlineTime(userId, usertoken)) {
//                                String infoString = "请重新登录!";
//                                int code = 4;
//                                toJson(response, infoString, code);
//
//                                return false;
//                            }
                            else {

                                return true;
                            }

                        }

                    }

                }
            } catch (Exception e) {

            }

        }
        return false;
    }


    private void toJson(HttpServletResponse response, String infoString, int code)
            throws JsonProcessingException, IOException {
        Result result = new Result();
        ObjectMapper mapper = new ObjectMapper();
        result.putMsg(infoString, false, code);
        String json = mapper.writeValueAsString(result.getResult());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(json);
    }

}
