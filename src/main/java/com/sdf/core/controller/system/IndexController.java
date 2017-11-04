package com.sdf.core.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.sdf.common.pojo.SessionUser;
import com.sdf.common.pojo.MSG;
import com.sdf.core.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台首页
 *
 * @author SDF
 * @date 2016年11月13日
 */
@Scope("prototype")
@Controller
@RequestMapping("/index/")
public class IndexController extends BaseController {

    /**
     * layuiCMS主页
     * http://localhost/Frame/index/layuiCMS
     *
     * @Date: 2017/11/4 13:44
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping(value = "layuiCMS")
    public String layuiCMS() {
        return BACK_PREFIX + "/system/index/layuiCMS";
    }

    /**
     * 提交登录
     *
     * @param session
     * @return
     * @throws Exception
     * @time 2016年10月8日 上午10:11:45
     */
    @RequestMapping("getMenu")
    @ResponseBody
    public Map<String, Object> getMenu(HttpSession session) throws Exception {
        Map<String, Object> result = new HashMap<>();

        SessionUser sessionUser = (SessionUser) session.getAttribute(BACK_SESSION_USER);

        if (sessionUser != null) {
            List<Map<String, Object>> fmenus = sessionUser.getFmenus();
            Map<String, List<Map<String, Object>>> scdMenuMap = sessionUser.getScdMenuMap();
            Map<String, List<Map<String, Object>>> thdMenuMap = sessionUser.getThdMenuMap();

            result.put("fmenus", fmenus);
            result.put("scdMenuMap", scdMenuMap);
            result.put("thdMenuMap", thdMenuMap);
            result.put("msg", MSG.createSuccessMSG());

        } else {
            result.put("msg", MSG.createErrorMSG("获取菜单失败，请重新登录"));
        }

        return result;
    }

}
