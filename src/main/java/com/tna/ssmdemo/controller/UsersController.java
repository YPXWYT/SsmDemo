package com.tna.ssmdemo.controller;


import java.util.List;
import java.util.stream.Collectors;

import com.tna.ssmdemo.entity.AjaxResponseBody;
import com.tna.ssmdemo.entity.SearchCriteria;
import com.tna.ssmdemo.entity.Users;
import com.tna.ssmdemo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST)
    public int addUser(@RequestBody Users users) {
       this.usersService.addUser(users);
        return 1;
    }



    /**
     * 查询全部用户
     */

    @PostMapping("/apishow")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors){
        AjaxResponseBody result = new AjaxResponseBody();

        if(errors.hasErrors()) {
            result.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        List<Users> users = this.usersService.findUserByName(search.getName());
        if(users.isEmpty()) {
            result.setMsg("no user found!");
        }else {
            result.setMsg("success");
        }
        result.setResult(users);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/apishow1")
    public List<Users> findUsersAll1(@RequestBody SearchCriteria search){
        return this.usersService.findUserByName(search.getName());
    }

    @PostMapping("/apishow2")
    public List<Users> findUsersAll2(@RequestBody Users user){
        return this.usersService.findUserByName(user.getName());
    }
    @PostMapping("/apishow3")
    public List<Users> findUsersAll3(){
        return this.usersService.findUserAll();
    }
}
