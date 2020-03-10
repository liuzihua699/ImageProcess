package com.zihua.opencv;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName example4
 * @Description TODO 直方图均衡化
 * @Author 刘子华
 * @Date 2020/3/10 19:35
 */
public class example4 {

    // 直方图均衡化
    public static void getHistImage(Mat img) {
        // 1. 直方图均衡化
        List<Mat> mats = new ArrayList<>();
        Core.split(img, mats);
        for (int i = 0; i < img.channels(); i++) {
            Imgproc.equalizeHist(mats.get(i), mats.get(i));
        }
        Mat dst = new Mat();
        Core.merge(mats, dst);
        // 2. 输出
        HighGui.imshow("原图-tiger.jpg", img);
        HighGui.imshow("直方图均衡化-tiger.jpg", dst);
        HighGui.moveWindow("原图-tiger.jpg", 350, 350);
        HighGui.moveWindow("直方图均衡化-tiger.jpg",1000, 350);
        HighGui.waitKey();
        System.exit(-1);
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("版本：" + Core.VERSION);

        final String NAME = "tiger.jpg";
        String path = new File(ClassLoader.getSystemResource(NAME).getPath()).getAbsolutePath();
        Mat img = Imgcodecs.imread(path);
        getHistImage(img);
    }
}
