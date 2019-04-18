package org.maxlv.shop.service;

import org.maxlv.shop.dto.ShopExecution;
import org.maxlv.shop.entity.Shop;

import java.io.File;


public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
