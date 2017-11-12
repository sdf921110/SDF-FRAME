package com.sdf.core.mapper.system;

import com.sdf.core.pojo.system.SysFileUrl;
import com.sdf.core.pojo.system.SysPosition;
import com.sdf.core.pojo.system.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 后台用户
 *
 * @Date: 2017/10/21 17:00
 * @Author: SDF
 * @Version: 1.0
 */
@Repository
public interface SysUserDao {

    int insert(SysUser sysUser);

    SysUser selectByCode(String code);

    SysUser selectById(int id);

    int update(SysUser sysUser);

    List<SysPosition> select(SysPosition sysPosition);
}
