package com.hp.service;

import com.hp.pojo.Master;
import com.hp.utils.JsonData;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface MasterService {
//    JsonData selectList(Integer page, Integer limit,String name,String phone) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    JsonData selectList(Integer page, Integer limit,Master master) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    JsonData updateStatus(Integer mid, Integer id);

    JsonData addMaster(Master master) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    JsonData updateByPrimaryKey(Master master);
}
