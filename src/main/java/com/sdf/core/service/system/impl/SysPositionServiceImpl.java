package com.sdf.core.service.system.impl;

import com.sdf.common.pojo.MSG;
import com.sdf.common.pojo.SessionUser;
import com.sdf.core.controller.BaseController;
import com.sdf.core.mapper.system.SysPositionDao;
import com.sdf.core.mapper.system.SysUserDao;
import com.sdf.core.pojo.system.SysPosition;
import com.sdf.core.pojo.system.SysUser;
import com.sdf.core.service.BaseService;
import com.sdf.core.service.system.ISysPositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
@Service
public class SysPositionServiceImpl extends BaseService implements ISysPositionService {

    @Resource
    private SysPositionDao sysPositionDao;

    @Override
    public Integer update(SysPosition sysPosition) {
        return null;
    }

    @Override
    public Integer deleteById(int id) {
        return null;
    }

    @Override
    public SysPosition selectById(int id) {
        return null;
    }

    @Override
    public void selectList(HashMap<String, Object> result, SysPosition sysPosition) {
        List<SysPosition> sysPositions = sysPositionDao.selectList(sysPosition);
        result.put("result", sysPositions);
        result.put("msg", MSG.createSuccessMSG());
    }
}
