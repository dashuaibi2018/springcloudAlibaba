package com.sujun.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sujun.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

    @Value("${server.port}")
    String port;

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PersonService personSrv;

    @RequestMapping("/getHi")
    public String getHi() {
        String url = "http://nacos-p/getPort";
        String res = restTemplate.getForObject(url, String.class);

        return "api-driver-hello:" + res;
    }

    @RequestMapping("/test")
    public String test() {
        System.out.println("api driver 参数：port：{},appName:{}" + appName);
        return "api-driver-hello:" + port + " " + appName;
    }

    @GetMapping("/get")
    public String getMessage() {

        String msg = personSrv.getMess();
        return msg;
    }


    @GetMapping("/np")
    public String getnp() {
        String msg = personSrv.getnp();
//        String msg1 = personSrv.getnp1();
        return msg;
    }

    @GetMapping("/getThread")
    public String getThreadMessage() {

        String msg = personSrv.getThreadMess();
        return msg;
    }


    @GetMapping("/get4")
    @SentinelResource(value = "get4")
    public String get4() {
        return "get4";
    }

    @GetMapping("/get5")
    @SentinelResource(value = "get5")
    public String get5() {
        return "get5";
    }

    /**
     * @param
     * @description: 异常降级
     * @return: java.lang.String
     * @author: sj
     * @time: 2021/5/10 2:30 下午
     */
    @GetMapping("/getexc")
    @SentinelResource(value = "getexc")
    public String getexc() throws Exception {
        int i = 10 / 0;
        return "getexc";
    }

    /**
     * @param
     * @description: 热点限流
     * @return: java.lang.String
     * @author: sj
     * @time: 2021/5/10 2:29 下午
     */
    @GetMapping("/getHot")
    @SentinelResource(value = "getHot")
    public String getHot(String name, int age) {
        return "name: " + name + "---age: " + age;
    }


}