package com.tna.ssmdemo.controller;

import com.tna.ssmdemo.entity.Users;
import com.tna.ssmdemo.service.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * rest 风格 api
 *
 * GET     /api/v1/books        所有书单
 * GET     /api/v1/books/{id}   获取一条书单
 * POST    /api/v1/books        新建一条书单
 * PUT     /api/v1/books/{id}   更新一条书单，提供全部信息
 * PATCH   /api/v1/books/{id}   更新一条书单，提供部分信息
 * DELETE  /api/v1/books/{id}   删除一条书单
 * DELETE  /API/v1/books        删除所有书单
 *
 */

@RequestMapping("/v1")
@RestController
public class ApiController {

    private final UsersService usersService;

    @Autowired
    public ApiController(UsersService usersService) {
        this.usersService = usersService;
    }
    /**
     * 获取所有书用户
     * GET     /api/v1/users        所有用户
     *
     * @return http 响应
     */
    @GetMapping("/users")
    public HttpEntity<?> users() {

        return new ResponseEntity<>(usersService.findUserAll(), HttpStatus.OK);
    }

    /**
     * 获取一个用户 * GET     /api/v1/users/{id}   获取一个用户
     * @param id id
     * @return http 响应
     */  @GetMapping("/users/{id}")
    public HttpEntity<?> usersOne(@PathVariable Integer id) {
        return new ResponseEntity<>(usersService.findUserById(id), HttpStatus.OK);
    }

    /**
     * 添加一个用户
     * POST    /api/v1/users        新建一个用户
     *
     * @param user 用户
     * @return http 响应
     */
    @PostMapping("/users")
    public HttpEntity<?> booksAdd(@Valid @RequestBody Users user, BindingResult bindingResult) {
        user.setId(null);
        return new ResponseEntity<>(usersService.addUser(user), HttpStatus.CREATED);
    }

    /**
     * 更新一个用户,提供一个用户的全部信息
     * PUT     /api/v1/users/{id}   更新一个用户，提供全部信息
     *
     * @param id 更新的id
     * @param user 更新后的用户
     * @return http 响应
     */
    @PutMapping("/users/{id}")
    public HttpEntity<?> booksPut(@Valid @PathVariable Integer id, @RequestBody Users user, BindingResult bindingResult) {
        Users exist = usersService.findUserById(id);
        user.setId(exist.getId());
        return new ResponseEntity<>(usersService.addUser(user), HttpStatus.OK);
    }

    /**
     * 更新一个用户,提供一个用户的部分信息
     * PATCH   /api/v1/users/{id}   更新一个用户，提供部分信息
     *
     * @param id 更新的id
     * @param user 更新后的用户
     * @return http 响应
     */
    @PatchMapping("/users/{id}")
    public HttpEntity<?> usersPatch(@PathVariable Integer id, @RequestBody Users user) {
        Users exist = usersService.findUserById(id);
        BeanWrapper beanWrapper = new BeanWrapperImpl(user);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor pd : propertyDescriptors) {
            if (beanWrapper.getPropertyValue(pd.getName()) == null) {
                nullPropertyNames.add(pd.getName());
            }
        }
        BeanUtils.copyProperties(user, exist, nullPropertyNames.toArray(new String[0]));
        return new ResponseEntity<>(usersService.findUserById(id), HttpStatus.OK);
    }

    /**
     * 删除一个用户
     * DELETE  /api/v1/users/{id}   删除一个用户
     *
     * @param id id
     * @return http 响应
     */
    @DeleteMapping("/users/{id}")
    public HttpEntity<?> usersDeleteOne(@PathVariable Integer id) {
        Users exist = usersService.findUserById(id);
        usersService.deleteUserById(exist.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 删除所有用户
     * DELETE  /API/v1/users        删除所有用户
     *
     * @return http 响应
     */
    @DeleteMapping("/users")
    public HttpEntity<?> usersDeleteAll() {
        List<Users> users = usersService.findUserAll();
        usersService.deleteUsersAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
