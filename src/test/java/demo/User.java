package demo;

/**
 * @Description:
 * @Date: 2017/11/17 14:43
 * @Author: SDF
 * @Version: 1.0
 */
public class User {


    private String name;

    private int age;

    private String tel;

    private String password;

    private String no;

    private char sex;

    private String email;

    public User(String name, String password,String email,String tel, char sex,  int age) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.sex = sex;
        this.age = age;
    }
}
