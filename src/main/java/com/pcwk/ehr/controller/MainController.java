package com.pcwk.ehr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class MainController {

    @GetMapping("/index.do")
    public String home() {
        return "/home";
    }

    @GetMapping("/calendar.do")
    public String calendar() {
        return "/board/calendar";
    }
}
