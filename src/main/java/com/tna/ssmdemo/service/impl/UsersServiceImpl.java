package com.tna.ssmdemo.service.impl;


import com.tna.ssmdemo.mapper.UsersMapper;
import com.tna.ssmdemo.entity.Users;
import com.tna.ssmdemo.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional /*表示在该类下所有的方法都受事务控制*/
public class UsersServiceImpl implements UsersService {

    @Resource/*或者@Autowired*/
    private UsersMapper usersMapper;

    @Override
    public Users addUser(Users users) {
        this.usersMapper.insertUser(users);
        return users;
    }

    @Override
    public List<Users> findUserAll() {
        return this.usersMapper.selectUsersAll();
    }

    @Override
    public Users findUserById(Integer id) {
        return this.usersMapper.selectUsersById(id);
    }

    @Override
    public Users updateUser(Users users) {
        this.usersMapper.updateUser(users);
        return users;
    }

    @Override
    public void deleteUserById(Integer id) {
        this.usersMapper.deleteUserById(id);
    }

    @Override
    public List<Users> findUserByName(String name) {
        return this.usersMapper.selectUsersByName(name);
    }

    @Override
    public void deleteUsersAll() {
        this.usersMapper.deleteUsersAll();
    }

}
