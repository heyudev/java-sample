package com.heyudev.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageUtil {

    public static void main(String[] argvs) throws Exception {
        //获取一张图片做底图
        BufferedImage bg = ImageIO.read(ImageUtil.class.getClassLoader().getResourceAsStream("bg.jpg"));
        Graphics2D g = bg.createGraphics();

        //设置字体/颜色
        Font font = new Font("微软雅黑", Font.PLAIN, 50);
        g.setFont(font);
        g.setColor(Color.BLACK);

        g.drawString("文字", 200, 150);

        //把另外一个图描画在底图上
        BufferedImage img = ImageIO.read(ImageUtil.class.getClassLoader().getResourceAsStream("icon.jpg"));
        //这个方法画全图，按照后两个参数设置的宽高缩放
        g.drawImage(img, 200, 200, 100, 100, null);

        //截取图片的一部分
        BufferedImage subImg = img.getSubimage(70, 70, 120, 120);
        g.drawImage(subImg, 20, 20, 60, 60, null);

        //保存图片
        ImageIO.write(bg, "jpeg", new File("target/newimg.jpg"));
    }


    public static String draw(){
        return "";
    }
}
