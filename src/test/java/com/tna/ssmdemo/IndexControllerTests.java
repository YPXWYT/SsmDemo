package com.tna.ssmdemo;

import cn.yiban.open.Authorize;
import com.tna.ssmdemo.utils.AppContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SsmdemoApplication.class)
public class IndexControllerTests {

//    @Test
//    public String getToken() {
//        Authorize authorize = new Authorize(AppContext.APPID, AppContext.APPSECRET);
//        String url = authorize.forwardurl(AppContext.BACKURL, "QUERY", Authorize.DISPLAY_TAG_T.WEB);
//        return "redirect:"+url;
//    }
}
