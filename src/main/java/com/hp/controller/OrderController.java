package com.hp.controller;

import com.hp.pojo.Order;
import com.hp.service.OrderService;
import com.hp.utils.JsonData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/selectOrdersList")
    public JsonData selectOrdersList(Integer page,Integer limit,String status,String phone){
        return orderService.selectOrdersList(page, limit, status, phone);
    }
    @RequestMapping("/addOrder")
    public JsonData addOrder(Order order,String  name,String car){
        return orderService.addOrder(order,name,car);
    }
//    @RequestMapping("/upd")
//    public JsonData upd(String status,Integer id){
//        return orderService.upd(id,status);
//    }
    @RequestMapping("/upd")
    public JsonData upd(Order order){
        JsonData jsonData = orderService.updateByOrderStatus(order);
        return jsonData;
    }

    @RequestMapping("/orderType")
    public JsonData orderType(Integer id){
        JsonData jsonData = orderService.selectByPrimaryKey(id);
        return jsonData;
    }

    @RequestMapping("/update")
    public JsonData update(Order order,String  name,String car){
        return orderService.update(order,name,car);
    }
    @RequestMapping("/liftOrder")
    public JsonData liftOrder(Order order){
        JsonData jsonData = orderService.liftOrder(order);
        return jsonData;
    }

    @RequestMapping("/ordersDown")
    public JsonData ordersDown(){
       List<Order> orders= orderService.allOrders();
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("订单列表");
        XSSFRow row = sheet.createRow(0);
        Cell[] cells=new Cell[6];
        for (int i = 0; i <cells.length ; i++) {
            cells[i]=row.createCell(i);
        }
        cells[0].setCellValue("订单编号");
        cells[1].setCellValue("客户");
        cells[2].setCellValue("手机号");
        cells[3].setCellValue("下单时间");
        cells[4].setCellValue("地址");
        cells[5].setCellValue("状态");

        for (int i = 0; i <orders.size() ; i++) {
            Row row1 = sheet.createRow(i + 1);
            Cell[] cells1=new Cell[6];
            for (int j=0;j<cells1.length;j++){
                cells1[j]=row1.createCell(j);
            }
            Order order = orders.get(i);
            cells1[0].setCellValue(order.getId());
            cells1[1].setCellValue(order.getCustomer().getName());
            cells1[2].setCellValue(order.getPhone());
            cells1[3].setCellValue(order.getCreatetime());
            cells1[4].setCellValue(order.getAddress());
            cells1[5].setCellValue(order.getStatus());
        }
        try{
            OutputStream os=new FileOutputStream("D:\\excel\\订单管理.xlsx");
            workbook.write(os);
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return JsonData.buildSuc("success");
    }
}
