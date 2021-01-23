package com.hp.service.imlp;

import com.hp.mapper.WaiterMapper;
import com.hp.pojo.Waiter;
import com.hp.service.WaiterService;
import com.hp.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WaiterServiceImpl implements WaiterService {
    @Autowired
    private WaiterMapper waiterMapper;
    public JsonData login(String account, String password) {
        if (account!=null&&password!=null){
            List<Waiter> waiters = waiterMapper.login(account, password);
            if (waiters.size()>0){
                return  JsonData.buildSuc(waiters.get(0));
            }
            return JsonData.buildError("用户名或不存在");
        }
        return JsonData.buildError("用户名或密码不能为空");
    }
}
