package com.zihua.opencv;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoWriter;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.net.URL;

/**
 * @ClassName example1
 * @Description TODO  OpenCV简单的显示
 * @Author 刘子华
 * @Date 2020/2/23 19:39
 */
public class example1 {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("版本：" + Core.VERSION);

        String imgPath = new File(ClassLoader.getSystemResource("tiger.jpg").getPath()).getAbsolutePath();
        Mat mat = Imgcodecs.imread(imgPath);
        ImageViewer imageViewer = new ImageViewer(mat, "示例图片--tiger");
        imageViewer.show();
    }
}
