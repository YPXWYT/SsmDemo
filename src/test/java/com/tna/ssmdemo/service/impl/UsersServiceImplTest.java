package com.tna.ssmdemo.service.impl;

import com.tna.ssmdemo.SsmdemoApplication;
import com.tna.ssmdemo.entity.Users;
import com.tna.ssmdemo.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SsmdemoApplication.class)
public class UsersServiceImplTest {

    @Autowired
    private UsersService usersService;

    @Test
    public void findUserById(){
        Users user = usersService.findUserById(5);
//        Assert.assertEquals(Integer.valueOf(5),user.getAge());//设置预期返回的数据值
        System.out.println("users:"+user.getName()+"+++"+user.getAge());
    }

    @Test
    public void findUserAll(){

        List<Users> list = usersService.findUserAll();
        for (int i=0;i<list.size();i++){
            System.out.println("user"+(i+1)+":"+list.get(i).getName()+"  "+list.get(i).getAge());
        }
        for (Users users:list){
            System.out.println(users.getName()+"--"+users.getAge());
        }
    }

    @Test
    public void addUser(){

        Users users = new Users();
//        List<Users> list = new ArrayList<>();
        users.setAge(19);
        users.setName("王玉兔");
        this.usersService.addUser(users);

    }

    @Test
    public void delUser(){

        this.usersService.deleteUserById(4);
    }
}