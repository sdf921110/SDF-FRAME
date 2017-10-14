package demo;

import com.sdf.core.mapper.demo.DemoUserDao;
import com.sdf.core.pojo.demo.DemoUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IDemoUserDaoTest {

    @Autowired
    private DemoUserDao demoUserDao;

    @Test
    public void testSelectById() throws Exception {
        long id = 1;
        DemoUser user = demoUserDao.selectById(id);
        if (user != null) {
            System.err.println(user.toString());
        } else {
            System.err.println("没有该用户！");
        }
    }

    @Test
    public void testSelectByName() throws Exception {
        String user_name = "SDF";
        DemoUser user = demoUserDao.selectByName(user_name);
        if (user != null) {
            System.err.println(user.toString());
        } else {
            System.err.println("没有该用户！");
        }
    }

    @Test
    public void testSelectAll() throws Exception {
        List<DemoUser> demoUsers = demoUserDao.selectAll();
        if (demoUsers != null) {
            for (DemoUser demoUser : demoUsers) {
                System.err.println(demoUser);
            }
        } else {
            System.err.println("没有用户！");
        }
    }

    @Test
    public void testInsert() throws Exception {
        DemoUser demoUser = new DemoUser("测试4", "123456", 21);
        Integer id = demoUserDao.insert(demoUser);
        if (id > 0) {
            System.err.println("添加成功！");
        } else {
            System.err.println("添加失败！");
        }
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = demoUserDao.deleteById(5);
        if (id > 0) {
            System.err.println("删除成功！");
        } else {
            System.err.println("删除失败！");
        }
    }

}
