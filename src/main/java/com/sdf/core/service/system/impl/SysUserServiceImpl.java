package com.sdf.core.service.system.impl;

import com.sdf.common.pojo.SessionUser;
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

        if(sysUser!=null){

            if(password.equals(sysUser.getPassword())){
                sessionUser.setSysUser(sysUser);
            }
        }

        return sessionUser;
    }
}
