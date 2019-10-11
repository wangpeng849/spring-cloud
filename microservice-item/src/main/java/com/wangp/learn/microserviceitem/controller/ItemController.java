package com.wangp.learn.microserviceitem.controller;


import com.wangp.learn.microserviceitem.config.JdbcConfigBean;
import com.wangp.learn.microserviceitem.entity.Item;
import com.wangp.learn.microserviceitem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 对外提供接口服务，查询商品信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "item/{id}")
    public Item queryItemById(@PathVariable("id") Long id) {
        return this.itemService.queryItemById(id);
    }

    @Autowired
    JdbcConfigBean jdbcConfigBean;

    @GetMapping(value ="/testconfig")
    public String testConfig(){
        return this.jdbcConfigBean.toString();
    }



    @RequestMapping("/test")
    public String test()  {
        return "test";
    }

}
