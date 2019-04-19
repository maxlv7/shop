package org.maxlv.shop.dto;

/*
 * 这个类的目的的封装执行后的结果
 */


import org.maxlv.shop.entity.Shop;
import org.maxlv.shop.enums.ShopStateEnum;

import java.util.List;

public class ShopExecution {

    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //店铺数量
    private int count;
    //操作的shop(增删改店铺的时候用)
    private Shop shop;
    //获取的shop列表(查询店铺列表的时候用)
    private List<Shop> shopList;

    //店铺操作失败时使用的构造器
    public ShopExecution(ShopStateEnum stateEnum){
        this.state = stateEnum.getState();
    }
    //店铺操作成功时使用的构造器(增删改成功时)
    public ShopExecution(ShopStateEnum stateEnum,Shop shop){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }
    //店铺操作成功时使用的构造器(查询成功时)
    public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public int getCount() {
        return count;
    }

    public Shop getShop() {
        return shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }
}

