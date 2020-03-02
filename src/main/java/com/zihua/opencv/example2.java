package com.zihua.opencv;

import lombok.val;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName example2
 * @Description TODO 图片直方图绘制
 * @Author 刘子华
 * @Date 2020/3/2 23:48
 */
public class example2 {
    
    // 直方图统计
    public static void hist() {
        final String NAME = "tiger.jpg";
        String path = new File(ClassLoader.getSystemResource(NAME).getPath()).getAbsolutePath();
        Mat img = Imgcodecs.imread(path);
        Mat grey = new Mat();
        
        // 1. 图片灰度化
        Imgproc.cvtColor(img, grey, Imgproc.COLOR_BGR2GRAY);
        List<Mat> mats = new LinkedList();
        mats.add(grey);
        Mat histogram = new Mat();
        MatOfFloat ranges = new MatOfFloat(0, 256);
        MatOfInt hist_size = new MatOfInt(256);
        // 2. 计算直方图
        Imgproc.calcHist(mats, new MatOfInt(0), new Mat(), histogram, hist_size, ranges, false);
        // 3. 创建直方图画板
        Mat histImage = Mat.zeros(100, (int) hist_size.get(0, 0)[0], CvType.CV_8UC1);
        // 4. 归一化直方图
        Core.normalize(histogram, histogram, 1, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());
        // 5. 绘制几何直方图
        for (int i = 0; i < (int) hist_size.get(0,0)[0]; i++) {
            Imgproc.line(histImage, 
                    new Point(i, histImage.rows()), 
                    new Point(i, histImage.rows() - Math.round(histogram.get(i, 0)[0])), 
                    new Scalar(255, 255, 255), 1, 8, 0);
        }

        HighGui.imshow("tiger原图", img);
        HighGui.imshow("tiger的直方图", histImage);
        HighGui.waitKey();
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("版本：" + Core.VERSION);
        hist();
    }
}
