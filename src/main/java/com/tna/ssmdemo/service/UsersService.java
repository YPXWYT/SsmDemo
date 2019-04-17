package com.tna.ssmdemo.service;


import java.util.List;

import com.tna.ssmdemo.entity.Users;

public interface UsersService {

    Users addUser(Users users);

    List<Users> findUserAll();

    Users findUserById(Integer id);

    Users updateUser(Users users);

    void deleteUserById(Integer id);

    List<Users> findUserByName(String name);

    void deleteUsersAll();
}
