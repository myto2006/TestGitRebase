package com.ai.opt.baas.bmc.test.api.pricemaking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.bmc.api.pricemaking.params.PriceElementInfoZX;
import com.ai.baas.bmc.api.pricemaking.params.ShoppingList;
import com.ai.baas.bmc.constants.BmcConstants;
import com.ai.baas.bmc.constants.BmcConstants.ZxServiceId;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class PricemakingSVRestTest {
    @Autowired
    protected ApplicationContext ctx;

    @Test
    public void queryPricemakingZX() {
        List<ShoppingList> shopping_lists = new ArrayList<ShoppingList>();
        // ECS
        Map<String, String> map = new HashMap<String, String>();
        map.put("ImageId", "win2324324.ss");
        map.put("InstanceType", "ecs.s2.large");
        map.put("RegionId", "cn-hongkong");
        map.put("DataDisk.1.Category", "cloud");
        map.put("systemDisk.Category", "cloud");
        map.put("InternetMaxBandwidthOut", "15");
        map.put("InternetChargeType", "PayByBandwidth");
        map.put("DataDisk.1.Size", "13");
        map.put("systemDisk.Size", "40");
        map.put("IoOptimized", "optimized");
        ShoppingList shoppingList1 = new ShoppingList();
        shoppingList1.setList_id("1");
        shoppingList1.setService_id("576206bb6ae6ca04e145958d");
        shoppingList1.setParameters(map);
//         shopping_lists.add(shoppingList1);
        // RDS
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("Engine", "MySQL");
        map2.put("DBInstanceClass", "rds.mssql.s1.small");
        map2.put("RegionId", "cn-hongkong");
        map2.put("DBInstanceStorage", "50");
        map2.put("DBInstanceNetType", "Internet");
        map2.put("PayType", "Postpaid");
        map2.put("InternetChargeType", "PayByTraffic");
        ShoppingList shoppingList2 = new ShoppingList();
        shoppingList2.setList_id("2");
        shoppingList2.setService_id(BmcConstants.ZxServiceId.RDS);
        shoppingList2.setParameters(map2);
//         shopping_lists.add(shoppingList2);
        // KVS
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("Capacity", "2");
        map3.put("RegionId", "cn-shanghai");
        ShoppingList shoppingList3 = new ShoppingList();
        shoppingList3.setList_id("3");
        shoppingList3.setService_id(BmcConstants.ZxServiceId.KVS);
        shoppingList3.setParameters(map3);
         shopping_lists.add(shoppingList3);
        // smartcloud
        ShoppingList smartCloud = new ShoppingList();
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("ServiceType", "基础");
        smartCloud.setParameters(parameters);
        smartCloud.setService_id(BmcConstants.ZxServiceId.SMART_CLOUD);
//        shopping_lists.add(smartCloud);
        //yonyouhr
        parameters = new HashMap<String, String>();
        parameters.put("appName", "YCC");
        parameters.put("Lease", "y1");
        parameters.put("Amount", "100");
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setParameters(parameters);
        shoppingList.setService_id(ZxServiceId.YONYOUHR);
//        shopping_lists.add(shoppingList);
        // yonyouyc
        parameters = new HashMap<String, String>();
        parameters.put("appGroup", "YC06");
        parameters.put("Amount", "100");
        shoppingList = new ShoppingList();
        shoppingList.setParameters(parameters);
        shoppingList.setService_id(ZxServiceId.YONYOUYC);
//        shopping_lists.add(shoppingList);

        PriceElementInfoZX request = new PriceElementInfoZX();
        request.setShopping_lists(shopping_lists);

        System.out.println(JSON.toJSONString(request));
        String s = HttpClientUtil.sendPost(
                "http://127.0.0.1:10884/baas-bmc/pricemaking/queryPricemakingZX",
                JSON.toJSONString(request));
        System.out.println("定价查询接口返回结果: " + s);
        System.out.println("success");
    }

}
