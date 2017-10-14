package com.sdf.core.controller.system;

import com.sdf.core.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 相关DEMO页面
 *
 * @Date: 2017/10/12 15:47
 * @Author: SDF
 * @Version: 1.0
 */
@Controller
@RequestMapping("/api/common/")
public class CommonController extends BaseController {

    /**
     * 百度 Web Uploader 上传图片页面
     *
     * http://localhost/Frame/api/common/webuploader?fileType=fileType&pathType=pathType&tableId=1&tableName=user&isThumb=0&isImg=1
     *
     * @return
     * @link <a>http://fex.baidu.com/webuploader/</a>
     */
    @RequestMapping(value = "webuploader")
    public String webuploader() {
        return BACK_PREFIX + "/common/uploadImages";
    }

}
