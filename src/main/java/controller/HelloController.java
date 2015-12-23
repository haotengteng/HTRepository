package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by htt on 2015/12/19.
 */
@Controller
@RequestMapping("/mvc")
public class HelloController {
    @ResponseBody
    @RequestMapping(value = "/hello")
    public String sayHello(String userName,String password) throws Exception {

        return "hello world";
    }

}
