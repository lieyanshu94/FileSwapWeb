package mao.fileswap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    // 处理根路径（"/"）的GET请求，返回对应的视图页面
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @PostMapping("/sss")
    @ResponseBody
    public String sss() {
        return "index";
    }
}