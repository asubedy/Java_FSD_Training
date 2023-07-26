package com.lemoncode21.springlogelk;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.ELKProject.demo.user.UserApplication;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@Slf4j
public class HomeController {
	Logger log=LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String HomePage(){
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("Welcome home Page " + localDateTime);
        return "Welcome to Home page";
    }

    @GetMapping("/logs")
    public String LogsPage(){
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("This Logs page " + localDateTime);
        return "Welcome to logs page";
    }

    @GetMapping("/warn")
    public String WarnPage(){
        LocalDateTime localDateTime = LocalDateTime.now();
        log.warn("This warn page " + localDateTime);
        return "Welcome to warn page";
    }


    @GetMapping("/er")
    public String ErrorPage(){
        LocalDateTime localDateTime = LocalDateTime.now();
        log.error("This error page " + localDateTime);
        return "Welcome to error page";
    }


}