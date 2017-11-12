package com.sdf.core.mapper.system;

import com.sdf.core.pojo.system.SysPosition;
import com.sdf.core.pojo.system.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 地址
 *
 * @Date: 2017/11/12 20:53
 * @Author: SDF
 * @Version: 1.0
 */
@Repository
public interface SysPositionDao {

    int insert(SysPosition sysPosition);

    SysPosition selectById(int id);

    int update(SysPosition sysPosition);

    List<SysPosition> selectList(SysPosition sysPosition);
}
