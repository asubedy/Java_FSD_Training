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
        log.info("Home page accessed at " + localDateTime);
        return "Home page. The logs can be seen in Kibana";
    }


    @GetMapping("/warn")
    public String WarnPage(){
        LocalDateTime localDateTime = LocalDateTime.now();
        log.warn("Warning page accessed at " + localDateTime);
        return "Warning page. Warning Logs has been sent to Kibana";
    }


    @GetMapping("/err")
    public String ErrorPage(){
        LocalDateTime localDateTime = LocalDateTime.now();
        log.error("Error page accessed at " + localDateTime);
        return "Error page! Error Logs has been sent to Kibana";
    }


}
