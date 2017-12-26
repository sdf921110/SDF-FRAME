package com.sdf.core.controller.system;

import com.sdf.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 代码生成
 *
 * @Date: 2017/12/26 21:53
 * @Author: SDF
 * @Version: 1.0
 */
@Controller
@RequestMapping("/sys-code/")
public class SysCodeController extends BaseController {

    /**
     * 代码生成页面
     *
     * @Date: 2017/12/26 21:53
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping(value = "page")
    public String page() {
        return BACK_PREFIX + "/system/code/page";
    }

}
