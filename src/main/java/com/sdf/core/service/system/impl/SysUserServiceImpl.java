package com.sdf.core.service.system.impl;

import com.sdf.common.exception.CustomRuntimeException;
import com.sdf.common.pojo.MSG;
import com.sdf.common.pojo.SessionUser;
import com.sdf.common.utils.StringUtil;
import com.sdf.core.controller.BaseController;
import com.sdf.core.mapper.system.SysFileUrlDao;
import com.sdf.core.mapper.system.SysUserDao;
import com.sdf.core.pojo.system.SysFileUrl;
import com.sdf.core.pojo.system.SysUser;
import com.sdf.core.service.BaseService;
import com.sdf.core.service.system.ISysFileUrlService;
import com.sdf.core.service.system.ISysUserService;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "common", key = "'selectByCode='+#code")
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
            if (StringUtil.MD5Encode(password).equals(sysUser.getPassword())) {
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
        result.put("msg", id > 0 ? MSG.createSuccessMSG("修改成功") : MSG.createErrorMSG("修改失败"));

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


    /**
     * 修改密码
     *
     * @param result
     * @param oldPwd
     * @param sysUser
     * @param session
     */
    @Override
    public void changePwdSubmit(HashMap<String, Object> result, String oldPwd, SysUser sysUser, HttpSession session) {
        try {
            SessionUser sessionUser = (SessionUser) session.getAttribute(BaseController.BACK_SESSION_USER);
            SysUser oldSysUser = null;
            if (sessionUser != null) {
                oldSysUser = sessionUser.getSysUser();
            }
            if (oldSysUser.getPassword().equals(StringUtil.MD5Encode(oldPwd))) {
                String newPwd = StringUtil.MD5Encode(sysUser.getPassword());
                if (oldSysUser.getPassword().equals(newPwd)){
                    result.put("msg", MSG.createErrorMSG("新密码不能与原密码相同，请重新输入"));
                    return;
                }
                updateCreate(sysUser);
                sysUser.setPassword(newPwd);
                int id = sysUserDao.update(sysUser);
                if (id > 0) {
                    // 更新session
                    if (oldSysUser.getId() == sysUser.getId()) {
                        sessionUser.setSysUser(sysUser);
                    }
                    result.put("msg", MSG.createSuccessMSG("密码修改成功"));
                } else {
                    result.put("msg", MSG.createErrorMSG("密码修改失败"));
                }
            } else {
                result.put("msg", MSG.createErrorMSG("原密码不正确，请重新输入"));
            }
        } catch (CustomRuntimeException e) {
            e.printStackTrace();
            logger.error("修改密码失败！" + e.getMessage(), e);
        }
    }


}
