package com.sdf.core.service.system.impl;

import com.sdf.common.pojo.MSG;
import com.sdf.common.pojo.SessionUser;
import com.sdf.core.controller.BaseController;
import com.sdf.core.mapper.system.SysFileUrlDao;
import com.sdf.core.mapper.system.SysUserDao;
import com.sdf.core.pojo.system.SysFileUrl;
import com.sdf.core.pojo.system.SysUser;
import com.sdf.core.service.BaseService;
import com.sdf.core.service.system.ISysFileUrlService;
import com.sdf.core.service.system.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * 后台用户实现类
 *
 * @Date: 2017/10/21 16:49
 * @Author: SDF
 * @Version: 1.0
 */
@Service
public class SysUserServiceImpl extends BaseService implements ISysUserService {

    @Resource
    private SysUserDao sysUserDao;

/*    @Override
    public Integer insert(SysUser sysUser) {
        return null;
    }*/

    @Override
    public Integer update(SysUser sysUser) {
        return null;
    }

    @Override
    public Integer deleteById(int id) {
        return null;
    }

    @Override
    public SysUser selectById(int id) {
        return null;
    }

    @Override
    public SysUser selectByPhone(String phone) {
        return null;
    }

    @Override
    public SysUser selectByCode(String code) {
        return sysUserDao.selectByCode(code);
    }

    @Override
    public List<SysUser> selectList() {
        return null;
    }

    /**
     * 登录验证
     */
    @Override
    public SessionUser login_submit(String code, String password, HttpServletRequest request) {

        SessionUser sessionUser = new SessionUser();

        SysUser sysUser = selectByCode(code);

        if (sysUser != null) {
            if (password.equals(sysUser.getPassword())) {
                sessionUser.setSysUser(sysUser);
            } else {
                sessionUser = null;
            }
        } else {
            sessionUser = null;
        }

        return sessionUser;
    }

    @Override
    public SysUser getInfo(Integer userId) {
        return null;
    }

    /**
     * 提交用户信息
     *
     * @param result
     * @param sysUser
     * @param session
     */
    @Override
    public void submit(HashMap<String, Object> result, SysUser sysUser, HttpSession session) {
        int id = 0;
        if (sysUser.getId() == null) {
            // 新增
            insertCreate(sysUser);
            id = sysUserDao.insert(sysUser);
        } else {
            // 编辑
            updateCreate(sysUser);
            id = sysUserDao.update(sysUser);
            if (id > 0) {
                SessionUser sessionUser = (SessionUser) session.getAttribute(BaseController.BACK_SESSION_USER);
                // 编辑的用户信息为当前登录用户，更新session
                if (sessionUser != null && sessionUser.getSysUser().getId() == sysUser.getId()) {
                    sessionUser.setSysUser(sysUser);
                }
            }
        }
        result.put("msg", id > 0 ? MSG.createSuccessMSG() : MSG.createErrorMSG());

/*
        if(sysUser.getId()==null){
            // 新增
            int insert = sysUserDao.insert(sysUser);
            if (insert > 0) {
                result.put("msg",MSG.createSuccessMSG());
            }
            result.put("msg",MSG.createErrorMSG());
        }else{
            // 编辑
            int update = sysUserDao.update(sysUser);
            if (update > 0) {
                SessionUser sessionUser = (SessionUser) session.getAttribute(BaseController.BACK_SESSION_USER);
                // 编辑的用户信息为当前登录用户，更新session
                if (sessionUser != null && sessionUser.getSysUser().getId() == sysUser.getId()) {
                    sessionUser.setSysUser(sysUser);
                }
                result.put("msg",MSG.createSuccessMSG());
            }
            result.put("msg",MSG.createErrorMSG());
        }
*/

    }
}
