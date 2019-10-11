package com.wangp.learn.microserviceorder.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wangp.learn.microserviceitem.entity.Item;
import com.wangp.learn.microserviceorder.feign.ItemFeignClient;
import com.wangp.learn.microserviceorder.properties.OrderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemService {

    // Spring框架对RESTful方式的http请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    OrderProperties orderProperties;

//    @Value("${myspcloud.item.url}")
//    private String itemUrl;

    /**无eureka实现方法
    public Item queryItemById(Long id) {
        return this.restTemplate.getForObject(orderProperties.getItemUrl().getUrl()
                + id, Item.class);
    }
    */
    //该方法走eureka注册中心调用(去注册中心根据app-item查找服务，这种方式必须先开启负载均衡@LoadBalanced)
    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById(Long id){
        String itemUrl = "http://app-item/item/{id}";
        Item result = restTemplate.getForObject(itemUrl,Item.class,id);
        System.out.println("订单系统调用商品服务，result:"+result);
        return result;
    }

    /**
     * Feign 改造
     * @param id
     * @return
     */
    @Autowired
    private ItemFeignClient itemFeignClient;

    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById2(Long id) {
        String itemUrl = "http://app-item/item/{id}";
        Item result = itemFeignClient.queryItemById(id);
        System.out.println("===========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
        return result;
    }

    /**
     * 不用hystrixCommand注解
     * @param id
     * @return
     */
//    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById3(Long id) {
        String itemUrl = "http://app-item/item/{id}";
        Item result = itemFeignClient.queryItemById(id);
        System.out.println("===========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
        return result;
    }


    public Item queryItemByIdFallbackMethod(Long id) {
        return new Item(id, "查询商品信息出错!", null, null, null);
    }
}
