package com.sujun.demo.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @SentinelResource(value = "get",blockHandler = "failback")
    public String getMess() {
        return "狗子醒醒";
    }

    @SentinelResource(value = "np")
    public String getnp() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "np醒醒";
    }

    @SentinelResource(value = "np1")
        public String getnp1() {

        return "np1醒醒";
    }


    @SentinelResource(value = "thread")
    public String getThreadMess() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "睡了一会儿，狗子醒醒";
    }



    public String failback(BlockException e){

        return "降级了" + e.getMessage();
    }
}