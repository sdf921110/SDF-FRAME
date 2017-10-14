package com.sdf.core.service.demo;

import com.sdf.core.pojo.demo.DemoUser;

import java.util.List;

public interface IDemoUserService {

    public DemoUser selectUserById(long id);

    public DemoUser selectUserByName(String user_name);

    public List<DemoUser> selectAll();

    public Integer insert(DemoUser demoUser);

    public Integer deleteById(int id);

}
