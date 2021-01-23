package com.hp.controller;

import com.hp.pojo.Order;
import com.hp.service.OrderService;
import com.hp.utils.JsonData;
import com.hp.utils.MapUtils;
import com.hp.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/cphone")
public class CustomerPhoneController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/isLogin")
    @ResponseBody
    public JsonData isLogin(HttpSession session){
        String phone = (String) session.getAttribute("phone");
        if (phone==null||"".equals(phone)){
        return JsonData.buildError("请登录。。。");
        }else {
            return JsonData.buildSuc("请稍后....");
        }
    }

    @RequestMapping("/sendSms")
    @ResponseBody
    public  JsonData sendSms(String phone){
        //发送验证码
        //生成验证码
        String fourRandom = SmsUtil.getFourRandom();
        System.out.println("验证码："+fourRandom);
        //发送短信
//        String s = SmsUtil.sendSms(phone,fourRandom);
        System.out.println("手机号："+phone);
//        System.out.println("短信发送消息："+s);
//        if (s.contains("")){
            //将验证码放入redis中
            Jedis jedis=new Jedis("localhost",6379);
            jedis.setex(phone,60,fourRandom);
            jedis.close();
            return JsonData.buildSuc(fourRandom);
        }
//        return JsonData.buildError("短信发送异常");
//    }
    @RequestMapping("/login")
    @ResponseBody
    public JsonData login(String phone,String code,HttpSession session){
        Jedis jedis=new Jedis("localhost",6379);
        String o=jedis.get(phone);
        jedis.close();
        if (!o.equals(code)) {
            return JsonData.buildError("验证码错误，请输入正确的验证码。。。");
            }
                else{
                session.setAttribute("phone",phone);
                return JsonData.buildSuc("登录成功...");
            }
        }
    @RequestMapping("/getMoney")
    @ResponseBody
    public String getMoney(String lng, String lat, String oldLng, String oldLat){
        int money =(int) MapUtils.algorithm(Double.valueOf(lng), Double.valueOf(lat), Double.valueOf(oldLng), Double.valueOf(oldLat))/100;
        return money+"";
    }
    @RequestMapping("/addOrder")
    @ResponseBody
    public JsonData addOrder(Order order, HttpSession session){
        //获取电话信息
        String phone = (String) session.getAttribute("phone");
        order.setPhone(phone);
        order.setStatus("0");
        order.setCreatetime(new Date());
        JsonData jsonData = orderService.saveOrder(order);
        return jsonData;
    }
    @RequestMapping("/selectListByPhone")
    @ResponseBody
    public JsonData selectListByPhone(HttpSession session){
        String phone = (String) session.getAttribute("phone");
        if(phone!=null){
            JsonData jsonData = orderService.selectListByPhone(phone);
            return jsonData;
        }
        return JsonData.buildError("error");
    }


}
