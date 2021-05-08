package com.sujun.demo.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {


    @SentinelResource(value = "np",blockHandler = "failback")
    public String getMess() {

        return "狗子醒醒";
    }

    public String failback(BlockException e){

        return "降级了" + e.getMessage();
    }

}