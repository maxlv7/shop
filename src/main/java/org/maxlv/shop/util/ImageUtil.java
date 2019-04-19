package org.maxlv.shop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class ImageUtil {
    //初始化Log
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    //classPath的基本路径
    public static String basePath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    //生成缩略图
    public static String generateThumbnail(File file, String targetAddr) throws IOException {
        //生成随机名
        String randName = getRandomFileName();
        //获取后缀名
        String ext = getFileExt(file);
        //创建文件夹
        mkdir(targetAddr);
        //创建真正的文件
        String realPath = targetAddr + randName + ext;
        File dest = new File(PathUtil.getImgBasePath() + realPath);

        if (dest.createNewFile()) {
            FileInputStream in = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream(dest);
            int n;// 每次读取的字节长度
            byte[] read = new byte[1024];// 存储每次读取的内容
            while ((n = in.read(read)) != -1) {
                out.write(read, 0, n);// 将读取的内容，写入到输出流当中
            }
        }
        return realPath;
    }

    //创建目标路径涉及到的目录 如/home/img/item
    //img item都要创建
    private static void mkdir(String targetAddr) {
        String realAddr = PathUtil.getImgBasePath() + targetAddr;
        File f = new File(realAddr);
        if (!f.exists()) {
            if (f.mkdirs()) {
                logger.info("成功创建文件夹:{}", f.getName());
            }
        }
    }

    //获取随机文件名
    private static String getRandomFileName() {
        int num = new Random().nextInt(89999) + 10000;
        return simpleDateFormat.format(new Date()) + num;
    }

    //获取文件后缀名
    private static String getFileExt(File file) {
        return file.getName().substring(file.getName().indexOf("."));
    }

}
