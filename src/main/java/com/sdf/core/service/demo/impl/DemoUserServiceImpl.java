package com.sdf.core.service.demo.impl;

import com.sdf.core.mapper.demo.DemoUserDao;
import com.sdf.core.pojo.demo.DemoUser;
import com.sdf.core.service.demo.IDemoUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("demoUserService")
public class DemoUserServiceImpl implements IDemoUserService {

    @Resource
    private DemoUserDao demoUserDao;

    public DemoUser selectUserById(long id) {
        return demoUserDao.selectById(id);
    }

    public DemoUser selectUserByName(String user_name) {
        return demoUserDao.selectByName(user_name);
    }

    public List<DemoUser> selectAll() {
        return demoUserDao.selectAll();
    }

    public Integer insert(DemoUser demoUser) {
        return demoUserDao.insert(demoUser);
    }

    public Integer deleteById(int id) {
        return demoUserDao.deleteById(id);
    }
}
