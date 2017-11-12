package com.sdf.common.resolver;

import com.sdf.core.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Date: 2017/11/9 10:26
 * @Author: SDF
 * @Version: 1.0
 */
public class MyExceptionHandler implements HandlerExceptionResolver {
    private Logger logger = Logger.getLogger(MyExceptionHandler.class);

    /**
     * 在这里处理所有得异常信息
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        //ex.printStackTrace();
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        ModelAndView mv = new ModelAndView(BaseController.BACK_PREFIX + "/common/error");
//        mv.addObject("title", ex.toString().replaceAll("\n", "<br/>"));
        mv.addObject("content", str.replaceAll("\r\n\t", "<br/><br/>"));
        return mv;
    }

//    @Override
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
//                                         Exception ex) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        logger.error(ex.getMessage(), ex);
//        if (ex instanceof MaxUploadSizeExceededException) {
//            map.put("msg", "文件大小超过限制!");
//            return new ModelAndView(new MappingJackson2JsonView(), map);
//        }
//        map.put("msg", "系统错误!");
//        return new ModelAndView(new MappingJackson2JsonView(), map);
//    }

    /**
     * 将错误信息添加到response中
     *
     * @param msg
     * @param response
     */
    public static void printWrite(String msg, HttpServletResponse response) {
        try {
            PrintWriter pw = response.getWriter();
            pw.write(msg);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
