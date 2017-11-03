package com.sdf.core.service.system;

import com.sdf.common.pojo.SessionUser;
import com.sdf.core.pojo.system.SysFileUrl;
import com.sdf.core.pojo.system.SysUser;
import com.sdf.core.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 后台用户接口
 *
 * @Date: 2017/10/21 16:43
 * @Author: SDF
 * @Version: 1.0
 */
public interface ISysUserService {

    /*public Integer insert(SysUser sysUser);*/

    public Integer update(SysUser sysUser);

    public Integer deleteById(int id);

    public SysUser selectById(int id);

    public SysUser selectByPhone(String phone);

    public SysUser selectByCode(String code);

    public List<SysUser> selectList();

    SessionUser login_submit(String code, String password, HttpServletRequest request);
}
