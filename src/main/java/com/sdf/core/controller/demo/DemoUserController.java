package com.sdf.core.controller.demo;

import com.sdf.core.pojo.demo.DemoUser;
import com.sdf.core.service.demo.IDemoUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/demo/user")
public class DemoUserController {

    @Resource
    private IDemoUserService demoUserService;

    @ResponseBody
    @RequestMapping("/queryById")
    public Map<String,Object> selectUser(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,Object> result = new HashMap<>();
        long id = Long.parseLong(request.getParameter("id"));
        DemoUser demoUser = demoUserService.selectUserById(id);
        result.put("data",demoUser);
        return result;
    }
}
