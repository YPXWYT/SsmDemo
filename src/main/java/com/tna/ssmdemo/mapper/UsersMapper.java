package com.tna.ssmdemo.mapper;


import java.util.List;

import com.tna.ssmdemo.entity.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {

    void insertUser(Users users);

    List<Users> selectUsersAll();

    Users selectUsersById(Integer id);

    void updateUser(Users users);

    void deleteUserById(Integer id);

    List<Users> selectUsersByName(String name);
    
    void deleteUsersAll();

}
