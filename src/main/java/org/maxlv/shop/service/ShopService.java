package org.maxlv.shop.service;

import org.maxlv.shop.dto.ShopExecution;
import org.maxlv.shop.entity.Shop;

import java.io.InputStream;


public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImg,String filename);
}
