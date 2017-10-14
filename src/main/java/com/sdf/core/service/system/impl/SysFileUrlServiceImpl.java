package com.sdf.core.service.system.impl;

import com.sdf.core.mapper.system.SysFileUrlDao;
import com.sdf.core.pojo.system.SysFileUrl;
import com.sdf.core.service.system.ISysFileUrlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统文件管理实现类
 *
 * @Date: 2017/10/12 16:29
 * @Author: SDF
 * @Version: 1.0
 */
@Service
public class SysFileUrlServiceImpl implements ISysFileUrlService {

    @Resource
    private SysFileUrlDao sysFileUrlDao;

    public Integer insert(SysFileUrl sysFileUrl) {
        return sysFileUrlDao.insert(sysFileUrl);
    }
}
