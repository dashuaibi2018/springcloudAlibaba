package com.sujun.demo.controller;

import com.sujun.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    PersonService personSrv;

    @GetMapping("/get")
    public String getMessage(){

        String msg = personSrv.getMess();
        return msg;
    }
}