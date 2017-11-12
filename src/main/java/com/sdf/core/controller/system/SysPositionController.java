package com.sdf.core.controller.system;

import com.sdf.common.exception.CustomRuntimeException;
import com.sdf.core.controller.BaseController;
import com.sdf.core.pojo.system.SysPosition;
import com.sdf.core.service.system.ISysPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * 地址
 *
 * @Date: 2017/11/10 16:29
 * @Author: SDF
 * @Version: 1.0
 */
@Scope("prototype")
@Controller
@RequestMapping("/api/address/")
public class SysPositionController extends BaseController {

    @Autowired
    private ISysPositionService sysPositionService;

    /**
     * 条件查询
     *
     * @Date: 2017/11/10 16:32
     * @Author: SDF
     * @Version: 1.0
     */
    @RequestMapping("select")
    @ResponseBody
    public HashMap<String, Object> select(SysPosition sysPosition) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            sysPositionService.selectList(result,sysPosition);
        } catch (CustomRuntimeException e) {
            throw new CustomRuntimeException(e.getMessage(), e);
        }
        return result;
    }


}
