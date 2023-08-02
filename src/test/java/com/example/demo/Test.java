package com.example.demo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

//参考原文链接：https://blog.csdn.net/lazy_p/article/details/7165999
public class Test {
    public static void binaryImage(BufferedImage image1,int k) throws IOException{
        BufferedImage image = image1;

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY
        for(int i= 0 ; i < width ; i++){
            for(int j = 0 ; j < height; j++){
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }
        ImageIO.write(grayImage, "jpg", new File("D:\\MinecraftModProject\\picture\\" + k + ".jpg"));
    }

    public static void grayImage() throws IOException{
        File file = new File("D:\\MinecraftModProject\\picture\\12.jpg");
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for(int i= 0 ; i < width ; i++){
            for(int j = 0 ; j < height; j++){
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }

        File newFile = new File("D:\\MinecraftModProject\\picture\\b.jpg");
        ImageIO.write(grayImage, "jpg", newFile);
    }
    public static void main(String[] args) throws IOException {
        String video = "D:\\MinecraftModProject\\1.mp4";
//        总4590帧图片    帧率*时长
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(video);
        grabber.start();
        grabber.setImageHeight(64);
        grabber.setImageWidth(128);
        Java2DFrameConverter converter = new Java2DFrameConverter();
        for (int i = 1; i <= 4590; i++) {
            Frame frame = grabber.grabImage();
            BufferedImage badApple = converter.convert(frame);
            binaryImage(badApple,i);
        }
        grabber.stop();
//        binaryImage();
//        grayImage();
    }
}
