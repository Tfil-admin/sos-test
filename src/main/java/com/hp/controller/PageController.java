package com.hp.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import javafx.scene.shape.Circle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/page")
public class PageController {
    @RequestMapping("/to_index")
    public String to_index (){
        return "/html/customerPhone/index.html";
    }
    @RequestMapping("/to_login")
    public String to_login (){
        return "/html/customerPhone/html/login.html";
    }

    @RequestMapping("/to_phoneList")
    public String to_phoneList(){
        return "/html/customerPhone/html/order.html";
    }

    @RequestMapping("/order")
    public String order(){
        return "/html/customerPhone/html/order.html";
    }
    @RequestMapping("/to_serverLogin")
    public String to_serverLogin(){
        return "/html/customerService/login.html";
    }

    @RequestMapping("/service_index")
    public String service_index(){
        return "/html/customerService/back.html";
    }
    @RequestMapping("/selectList")
    public String selectList(){
        return "/html/orders/table.html";
    }
    @RequestMapping("/to_addOrder")
    public String to_addOrder(){
        return "/html/orders/addOrder.html";
    }
    @RequestMapping("/to_upd")
    public String to_upd(){
        return "/html/orders/updOrder.html";
    }
    @RequestMapping("/updStatus")
    public String updStatus(){
        return "/html/orders/updStatus.html";
    }
    @RequestMapping("/generateCheckCode")
    public void generateCheckCode(HttpSession session, HttpServletResponse response) throws IOException {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        session.setAttribute("code",captcha.getCode());
        captcha.write(response.getOutputStream());
    }

    @RequestMapping("/to_masterSelectList")
    public String to_masterSelectList(){
        return "/html/master/table.html";
    }
    @RequestMapping("/to_masterAdd")
    public String to_masterAdd(){
        return "/html/master/addMaster.html";
    }
    @RequestMapping("/to_MasterUpd")
    public String to_MasterUpd(){
        return "/html/master/updMaster.html";
    }
    @RequestMapping("/to_sendMaster")
    public String to_sendMaster(){
        return "/html/orders/master/table.html";
    }

    @RequestMapping("/to_customerSelectList")
    public String to_customerSelectList(){
        return "/html/customer/table.html";
    }
    @RequestMapping("/to_customerUpd")
    public String to_customerUpd(){
        return "/html/customer/updCustomer.html";
    }
}
