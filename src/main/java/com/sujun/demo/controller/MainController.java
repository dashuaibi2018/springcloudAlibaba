package com.sujun.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
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


    @GetMapping("/np")
    public String getnp(){
        String msg = personSrv.getnp();
//        String msg1 = personSrv.getnp1();
        return msg;
    }

    @GetMapping("/getThread")
    public String getThreadMessage(){

        String msg = personSrv.getThreadMess();
        return msg;
    }


    @GetMapping("/get4")
    @SentinelResource(value = "get4")
    public String get4(){
        return "get4";
    }

    @GetMapping("/get5")
    @SentinelResource(value = "get5")
    public String get5(){
        return "get5";
    }

    /**
     * @description: 异常降级
     * @param
     * @return: java.lang.String
     * @author: sj
     * @time: 2021/5/10 2:30 下午
     */
    @GetMapping("/getexc")
    @SentinelResource(value = "getexc")
    public String getexc() throws Exception{
        int i =10/0;
        return "getexc";
    }

    /**
     * @description: 热点限流
     * @param
     * @return: java.lang.String
     * @author: sj
     * @time: 2021/5/10 2:29 下午
     */
    @GetMapping("/getHot")
    @SentinelResource(value = "getHot")
    public String getHot(String name ,int age){
        return "name: "+name +"---age: "+age;
    }


}