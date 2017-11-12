package com.sdf.core.service.system;

import com.sdf.common.pojo.SessionUser;
import com.sdf.core.pojo.system.SysPosition;
import com.sdf.core.pojo.system.SysUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * 地址
 *
 * @Date: 2017/11/10 16:29
 * @Author: SDF
 * @Version: 1.0
 */
public interface ISysPositionService {

    public Integer update(SysPosition sysPosition);

    public Integer deleteById(int id);

    public SysPosition selectById(int id);

    public void selectList(HashMap<String, Object> result,SysPosition sysPosition);

}
