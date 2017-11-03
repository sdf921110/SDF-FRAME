package com.sdf.core.mapper.system;

import com.sdf.core.pojo.system.SysFileUrl;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Date: 2017/10/12 16:31
 * @Author: SDF
 * @Version: 1.0
 */
@Repository
public interface SysFileUrlDao {

    int insert(SysFileUrl sysFileUrl);

   /* int insertSelective(SysFileUrl sysFileUrl);*/

}
