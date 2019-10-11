package com.wangp.learn.microserviceorder.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 以myspcloud开头的配置被匹配到
 * @author Evan
 */
@Component
@ConfigurationProperties(prefix="myspcloud")
public class OrderProperties {

    private ItemProperties itemUrl = new ItemProperties();

    public ItemProperties getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(ItemProperties itemUrl) {
        this.itemUrl = itemUrl;
    }
}
