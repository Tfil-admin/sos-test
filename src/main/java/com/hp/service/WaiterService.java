package com.hp.service;

import com.hp.utils.JsonData;


public interface WaiterService {
    JsonData login(String account, String password);
}
