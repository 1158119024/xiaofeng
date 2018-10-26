package com.xiaofeng.blogs.health;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/19 10:41
 * @Description:
 */
@Controller
@RequestMapping("/provider")
public class HealthConsumerController {

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health(){
        return "redirect:http://127.0.0.1:8001/health";
    }
}
