package com.tna.ssmdemo.controller;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.Friend;
import cn.yiban.open.common.User;
import com.tna.ssmdemo.entity.Users;
import com.tna.ssmdemo.service.UsersService;
import com.tna.ssmdemo.utils.AppContext;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class IndexController {

    @Resource
    private UsersService usersService;
    /**
     * 页面跳转
     * 这种方式在访问时则不需要为页面专门配置一个mapping
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    /**
     * 添加用户
     */
    @RequestMapping("/addUser")
    public String addUser(Users users) {
        this.usersService.addUser(users);
        return "ok";
    }

    /**
     * 根据用户Id查询用户
     */
    @RequestMapping("/findUserById")
    public String findUserById(Integer id, Model model) {
        Users user = this.usersService.findUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }


    /**
     * 更新用户
     */
    @RequestMapping("/editUser")
    public String editUser(Users users) {
        this.usersService.updateUser(users);
        return "redirect:/user/findUserAll";
    }

    /**
     * 删除用户
     */
    @RequestMapping("/delUser")
    public String delUser(Integer id) {
        this.usersService.deleteUserById(id);
        return "redirect:/user/apishow3";
    }

    @RequestMapping("/getToken")
    public String getToken() {
        Authorize authorize = new Authorize(AppContext.APPID, AppContext.APPSECRET);
        String url = authorize.forwardurl(AppContext.BACKURL, "QUERY", Authorize.DISPLAY_TAG_T.WEB);
        return "redirect:"+url;
    }

    @RequestMapping("/auth")
    public void login(HttpServletRequest request, HttpServletResponse response)throws Exception{

        String code = request.getParameter("code");
        if (code == null || code.isEmpty())
        {
            response.sendRedirect("index.html");
        }
        HttpSession session = request.getSession();

//        String appid   = (String) session.getAttribute(AppContext.APPID);
//        String seckey  = (String) session.getAttribute(AppContext.APPSECRET);
//        String backurl = (String) session.getAttribute(AppContext.BACKURL);

        Authorize auth = new Authorize(AppContext.APPID,AppContext.APPSECRET);
        String text = auth.querytoken(code,AppContext.BACKURL);

        String getUrl = null;


//        JSONObject json = JSONObject.fromObject(auth.querytoken(code,AppContext.BACKURL));
//        String accessToken = json.getString("access_token");
//        request.getSession().setAttribute("access_token", accessToken);
//        User user = new User(accessToken);
//        request.getSession().setAttribute("user", user);
//        response.sendRedirect("ok.html");

        if (text == null || text.isEmpty())
        {
            response.sendRedirect("index.html");
        }
        JSONObject json = JSONObject.fromObject(text);

        if (json == null)
        {
            response.sendRedirect("index.html");
        }

        if (json.has("access_token"))
        {
            if (json.has("userid"))
            {
                session.setAttribute("userid", json.getString("userid"));
            }
            if (json.has("expires"))
            {
                session.setAttribute("expires", json.getString("expires"));
            }
            session.setAttribute("token", json.getString("access_token"));
            String accessToken = json.getString("access_token");

            String text1 = auth.getManInstance(accessToken).query();
            System.out.println(text1);

            User user = new User(accessToken);
            JSONObject userInfo = JSONObject.fromObject(user.me()).getJSONObject("info");
            JSONObject otherUserInfo = JSONObject.fromObject(user.other(2));
            String payUser =userInfo.getString("yb_userid");

                    Friend friend = new Friend(accessToken);
            JSONObject me_list = JSONObject.fromObject(friend.list(1,15));
            String text2 = auth.getManInstance(accessToken).revoke();
            System.out.println(text2);
             getUrl = AppContext.PAY_API + "?" + "access_token="
                    + accessToken + "&pay=" + 90 + "&sign_back=" + AppContext.BACKURL
                    + "&yb_userid=" + payUser;


            System.out.println(otherUserInfo);
            System.out.println(userInfo);
            System.out.println(me_list);
            session.setAttribute("user",user);
            System.out.println(getUrl);
            
        }
        else
        {
            System.out.println("failed");
        }
        request.getRequestDispatcher("/v1/users").forward(request,response);
//        response.sendRedirect("/user/show.html");
//        response.sendRedirect(getUrl);
    }

    @RequestMapping("/auth01")
    public String getCode() {
//        HttpServletRequest request = null;
//        String URL = null;
////        Authorize au = new Authorize(AppContext.APPID, AppContext.APPSECRET);
////        String code = request.getParameter("code");
//        if ( code == null || code.isEmpty() )
//        {
//            String url = au.forwardurl(AppContext.BACKURL, "test", Authorize.DISPLAY_TAG_T.WEB);
//            System.out.println("ok");
//             URL = "redirect:"+url;
//        }else {
//            URL = "redirect:http://127.0.0.1:8080/user/yiban.html";
//        }


        return "redirect:http://127.0.0.1:8080/user/yiban.html";
    }
}