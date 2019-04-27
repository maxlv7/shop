package org.maxlv.shop.web.shopadmin;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.maxlv.shop.dto.ShopExecution;
import org.maxlv.shop.entity.Area;
import org.maxlv.shop.entity.Shop;
import org.maxlv.shop.entity.ShopCategory;
import org.maxlv.shop.enums.ShopStateEnum;
import org.maxlv.shop.service.AreaService;
import org.maxlv.shop.service.ShopCategoryService;
import org.maxlv.shop.service.ShopService;
import org.maxlv.shop.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    private ShopService service;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getshopinitinfo() {
        Map<String, Object> data = new HashMap<>();

        try {
            //模拟数据
            ShopCategory sc = new ShopCategory();
            ShopCategory sc1 = new ShopCategory();
            sc1.setShopCategoryId(10L);
            sc.setParent(sc1);

            List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(sc);
            List<Area> areaList = areaService.getAreaList();
            data.put("success", true);
            data.put("shopCategoryList", shopCategoryList);
            data.put("areaList", areaList);
        } catch (Exception e) {
            data.put("success", false);
            data.put("errMsg", e.getMessage());
        }
        return data;
    }

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest req) {
        Map<String, Object> data = new HashMap<>();
        //接收并转化相应的参数
        String str = HttpServletRequestUtil.getString(req, "shopstr");
        //使用jackson转化为类对象
        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop;
        try {
            shop = objectMapper.readValue(str, Shop.class);
        } catch (Exception e) {
            data.put("success", false);
            data.put("errMsg", "参数错误!");
            return data;
        }
        //图片处理

        CommonsMultipartFile shopImg;
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(req.getSession().getServletContext());
        if (resolver.isMultipart(req)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) req;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            data.put("success", false);
            data.put("errMsg", "图片不能为空");
            return data;
        }
        //注册店铺
        if (shop != null && shopImg != null) {
//            PersonInfo personInfo = new PersonInfo();
            //这个userID是可以从session中获取的
            //TODO session获取
//            personInfo.setUserId(8L);
//            shop.setOwner(personInfo);
            //返回是一个结果包装类的实例
            //直接用输入流写入文件
            ShopExecution se;
            try {
                se = service.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());

                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    data.put("success", true);
                } else {
                    data.put("success", false);
                    data.put("errMsg", se.getStateInfo());
                }
                return data;
            } catch (IOException e) {
                return data;
            }
        } else {
            data.put("success", false);
            data.put("errMsg", "请核对信息!");
            return data;
        }

    }
}


