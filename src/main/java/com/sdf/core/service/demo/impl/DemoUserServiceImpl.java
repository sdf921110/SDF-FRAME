package com.sdf.core.service.demo.impl;

import com.sdf.core.mapper.demo.DemoUserDao;
import com.sdf.core.pojo.demo.DemoUser;
import com.sdf.core.service.BaseService;
import com.sdf.core.service.demo.IDemoUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoUserServiceImpl extends BaseService implements IDemoUserService{

    @Resource
    private DemoUserDao demoUserDao;

    @Override
    public DemoUser selectUserById(int id) {
        return demoUserDao.selectById(id);
    }

    @Override
    public DemoUser selectUserByName(String user_name) {
        return demoUserDao.selectByName(user_name);
    }

    @Override
    public List<DemoUser> selectAll() {
        return demoUserDao.selectAll();
    }

    @Override
    public Integer insert(DemoUser demoUser) {
        return demoUserDao.insert(demoUser);
    }

    @Override
    public Integer deleteById(int id) {
        return demoUserDao.deleteById(id);
    }
}
