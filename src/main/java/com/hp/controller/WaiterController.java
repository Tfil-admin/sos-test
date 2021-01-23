package com.hp.controller;

import com.hp.pojo.Order;
import com.hp.pojo.Waiter;
import com.hp.service.WaiterService;
import com.hp.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/waiter")
public class WaiterController {
@Autowired
    private WaiterService waiterService;
    @RequestMapping("/login")
    @ResponseBody
    public JsonData login(String account, String password, String verifyCode, HttpSession session){
        String code = (String) session.getAttribute("code");
        if (verifyCode !=null&& verifyCode.equals(code)){
            JsonData login = waiterService.login(account, password);
            session.setAttribute("customer",login.getData());
            return login;
        }
        return JsonData.buildError("验证码输入错误");
    }
    @RequestMapping("/upd")
    @ResponseBody
    public  JsonData upd(Order order,String name, String car){
        return JsonData.buildError("验证码输入错误");
    }


}
