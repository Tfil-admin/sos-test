package com.hp.controller;

import com.hp.pojo.Master;
import com.hp.service.MasterService;
import com.hp.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/master")
public class MasterController {
    @Autowired
    private MasterService masterService;
//    @RequestMapping("/selectList")
//    public JsonData selectList(Integer page,Integer limit,String name,String phone)
//            throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        return  masterService.selectList(page, limit,name,phone);
//    }
    @RequestMapping("/selectList")
    public JsonData selectList(Integer page,Integer limit,Master master)throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return  masterService.selectList(page, limit,master);
    }
    @RequestMapping("/selectMasterList")
    public JsonData selectMasterList(Integer page,Integer limit,Master master) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return  masterService.selectList(page, limit,master);
    }
    @RequestMapping("/sendMaster")
    public JsonData sendMaster(Integer mid, Integer id){
        return masterService.updateStatus(mid, id);
    }
    @RequestMapping("/addMaster")
    public JsonData addMaster(Master master) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return masterService.addMaster(master);
    }
    @RequestMapping("/masterUpd")
    public JsonData masterUpd(Master master){
        return masterService.updateByPrimaryKey(master);
    }
}
