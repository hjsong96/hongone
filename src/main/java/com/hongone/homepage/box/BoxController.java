package com.hongone.homepage.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoxController {

    @GetMapping("/boxOrderInfo")
    public String boxOrderInfo() {
        return "boxOrderInfo";
    }
}
