package com.hp.service.imlp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hp.mapper.MasterAddressMapper;
import com.hp.mapper.MasterMapper;
import com.hp.mapper.OrderMapper;
import com.hp.pojo.Master;
import com.hp.pojo.MasterAddress;
import com.hp.pojo.Order;
import com.hp.service.MasterService;
import com.hp.utils.JsonData;
import com.hp.utils.LngAndLatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MasterServiceImpl implements MasterService {
    @Autowired
    private MasterMapper masterMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MasterAddressMapper masterAddressMapper;

//    public JsonData selectList(Integer page, Integer limit,String name,String phone) {
//        PageHelper.startPage(page,limit);
//        List<Master> list = masterMapper.selectList(phone, name);
//        PageInfo<Master> pageInfo=new PageInfo<Master>(list);
//        return JsonData.buildSuc(pageInfo);
//    }

    public JsonData selectList(Integer page, Integer limit,Master m)  {
        List<Master> masters=null;
        PageHelper.startPage(page,limit);
        if (m.getName()!=null){
            masters=masterMapper.selectAllAndLike(m);
        }else {
            masters=masterMapper.selectAll();
        }
        for (Master master:masters
             ) {
            String lng = master.getMasterAddress().getLng();
            String lat = master.getMasterAddress().getLat();
            try{
                String lngAndLat = LngAndLatUtil.getLngAndLat(lng, lat);
                master.getMasterAddress().setAddress(lngAndLat);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        PageInfo<Master> masterPageInfo=new PageInfo<Master>(masters);
        return JsonData.buildSuc(masterPageInfo);
    }

    public JsonData updateStatus(Integer mid, Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        order.setStatus("1");
        Master master = masterMapper.selectByPrimaryKey(mid);
            if (master.getMasterAddress().getStatus().equals("0")){
            return JsonData.buildError("该技术在忙，请更换人选哦");
        }
        master.getMasterAddress().setStatus("0");
        master.setDid(String.valueOf(order.getId()));
        int i = orderMapper.updateByOrderStatus(order);
        int i1 = masterMapper.updateByPrimaryKey(master);
        int i2 = masterAddressMapper.updateByPrimaryKey(master.getMasterAddress());
        if (i>0&&i1>0&&i2>0){
            return JsonData.buildSuc("派单成功");
        }
        return JsonData.buildError("派单失败");
    }

    public JsonData addMaster(Master master) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map map = LngAndLatUtil.getLngAndLat(master.getAddress());
        MasterAddress masterAddress = new MasterAddress();
        masterAddress.setLng((String) map.get("lng"));
        masterAddress.setLat((String) map.get("lat"));
        masterAddress.setStatus("1");
        int result = masterMapper.insert(master);
        masterAddress.setMid(master.getId());
        int result1 = masterAddressMapper.insert(masterAddress);
        if (result>0 && result1>0){
            return JsonData.buildSuc("添加成功");
        }
        return JsonData.buildError("添加失败");
    }

    public JsonData updateByPrimaryKey(Master master) {
        int result = masterMapper.updateByPrimaryKey(master);
        if (result>0){
            return JsonData.buildSuc("修改成功");
        }
        return JsonData.buildError("修改失败");
    }
}
