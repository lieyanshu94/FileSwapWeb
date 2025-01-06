package mao.fileswap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 处理根路径（"/"）的GET请求，返回对应的视图页面
    @GetMapping("/")
    public String home() {
        return "index";
    }
}