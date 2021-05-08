package com.sujun.demo;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class SentinelTest {

    public static void main(String[] args) {
        initFlowRules();
        int i = 100;
        while (i != 0) {
            Entry entry = null;
            i--;
            try {
                entry = SphU.entry("HelloWorld");
            } catch (BlockException e) {
                System.out.println("block");
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }

            System.out.println("HelloWorld: " + i);
        }

        System.exit(1);


    }

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }


}