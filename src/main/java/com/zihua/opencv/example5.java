package com.zihua.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;

/**
 * @ClassName example5
 * @Description TODO 中值滤波平滑
 * @Author 刘子华
 * @Date 2020/3/10 19:53
 */
public class example5 {
    
    // 中值滤波
    public static void getMedian(Mat img) {
        // 1. img中值滤波
        Mat dist = new Mat();
        Imgproc.medianBlur(img, dist, 5);
        // 2. 输出
        HighGui.imshow("原图-tiger.jpg", img);
        HighGui.imshow("中值滤波平滑-tiger.jpg", dist);
        HighGui.moveWindow("原图-tiger.jpg", 350, 350);
        HighGui.moveWindow("中值滤波平滑-tiger.jpg",1000, 350);
        HighGui.waitKey();
        System.exit(-1);
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("版本：" + Core.VERSION);

        final String NAME = "tiger.jpg";
        String path = new File(ClassLoader.getSystemResource(NAME).getPath()).getAbsolutePath();
        Mat img = Imgcodecs.imread(path);
        getMedian(img);
    }
}
