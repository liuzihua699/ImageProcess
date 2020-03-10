package com.zihua.opencv;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName example6
 * @Description TODO 锐化
 * @Author 刘子华
 * @Date 2020/3/11 0:40
 */
public class example6 {
    
    public static void getSharpenImage(Mat img) {
        // 1. 定义内核
        Mat kernel = new Mat(3, 3, CvType.CV_32F, new Scalar(0));
        kernel.put(1, 1, 5.0);
        kernel.put(0, 1, -1.0);
        kernel.put(2, 1, -1.0);
        kernel.put(1, 0, -1.0);
        kernel.put(1, 2, -1.0);
        Mat dst = new Mat();
        Imgproc.filter2D(img, dst, img.depth(), kernel);
        // 2. 输出
        HighGui.imshow("原图-tiger.jpg", img);
        HighGui.imshow("锐化-tiger.jpg", dst);
        HighGui.moveWindow("原图-tiger.jpg", 350, 350);
        HighGui.moveWindow("锐化-tiger.jpg",1000, 350);
        HighGui.waitKey();
        System.exit(-1);
    }
    
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("版本：" + Core.VERSION);

        final String NAME = "tiger.jpg";
        String path = new File(ClassLoader.getSystemResource(NAME).getPath()).getAbsolutePath();
        Mat img = Imgcodecs.imread(path);
        getSharpenImage(img);
    }
}
