package com.merrill.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-02-12
 * Time: 14:59
 * Description:
 */

@Controller
@RequestMapping("/user")
public class StepController {

    @RequestMapping("/repairmentApply")
    public String repairmentApply(){
        return "repairmentApply";
    }

    @RequestMapping("/nextStep")
    @ResponseBody
    public String nextStep(){
        return null;
    }
}
