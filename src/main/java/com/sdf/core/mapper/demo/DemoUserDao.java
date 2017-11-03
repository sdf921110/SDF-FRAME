package com.sdf.core.mapper.demo;

import com.sdf.core.pojo.demo.DemoUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemoUserDao {

    DemoUser selectById(long id);

    DemoUser selectByName(String user_name);

    List<DemoUser> selectAll();

    Integer insert(DemoUser demoUser);

    Integer deleteById(int id);

}
