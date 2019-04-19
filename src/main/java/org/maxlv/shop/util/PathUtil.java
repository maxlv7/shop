package org.maxlv.shop.util;

public class PathUtil {
    //文件分隔符
    private static final String separator = System.getProperty("file.separator");

    //获取图片存放的根目录
    public static String getImgBasePath(){
        //获取os
        String os = System.getProperty("os.name");
        String basePath;
        if (os.toLowerCase().startsWith("win")){
            basePath = "D:/img";
        }else{
            basePath = "/home/img";
        }
        basePath = basePath.replace("/",separator);
        return basePath;
    }
    public static String getShopImgPath(Long ShopId){
        return ("/upload/item/shop/"+ShopId+"/").replace("/",separator);
    }

}
