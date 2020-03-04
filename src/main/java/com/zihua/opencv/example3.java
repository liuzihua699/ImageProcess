package com.zihua.opencv;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName dft
 * @Description TODO OpenCV绘制图片离散傅里叶变换
 * @Author 刘子华
 * @Date 2020/3/3 2:48
 */
public class example3 {
    public static void getDftImage(Mat img){
        // 1. 扩充图像边界(图像大小优化)
        int M = Core.getOptimalDFTSize(img.rows()); // 获得最佳DFT尺寸，为2的次方
        int N = Core.getOptimalDFTSize(img.cols()); // 同上
        Mat padded = new Mat();
        Core.copyMakeBorder(img, padded, 0, M - img.rows(), 0, N - img.cols(), Core.BORDER_CONSTANT, new Scalar(0));
        
        // 2. 图像类型转换
        padded.convertTo(padded, CvType.CV_32F);
        List<Mat> allPlanes = new ArrayList<Mat>();
        Core.split(padded, allPlanes);
        if (allPlanes.size() > 1) {
            for (int i = 0; i < allPlanes.size(); i++) {
                if (i == 0) {
                    padded = allPlanes.get(i);
                    break;
                }
            }
        } else {
            padded = img;
        }
        
        // 3. 实部和虚部矩阵创建
        Mat complexImage = new Mat();
        List<Mat> planes = new ArrayList<Mat>();
        planes.add(padded);
        planes.add(Mat.zeros(padded.size(), CvType.CV_32F));
        Core.merge(planes, complexImage);
        
        // 4. dft实现
        Core.dft(complexImage, complexImage);
        
        // 5. 频谱计算
        List<Mat> newPlanes = new ArrayList<Mat>();
        Mat mag = new Mat();
        Core.split(complexImage, newPlanes);
        Core.magnitude(newPlanes.get(0), newPlanes.get(1), mag);
        Core.add(Mat.ones(mag.size(), CvType.CV_32F), mag, mag);
        
        // 6. 对数坐标转换
        Core.log(mag, mag);
        
        // 7. 频谱平移
        mag = mag.submat(new Rect(0, 0, mag.cols() & -2, mag.rows() & -2));
        int cx = mag.cols() / 2;
        int cy = mag.rows() / 2;
        Mat q0 = new Mat(mag, new Rect(0, 0, cx, cy));
        Mat q1 = new Mat(mag, new Rect(cx, 0, cx, cy));
        Mat q2 = new Mat(mag, new Rect(0, cy, cx, cy));
        Mat q3 = new Mat(mag, new Rect(cx, cy, cx, cy));
        Mat tmp = new Mat();
        q0.copyTo(tmp);
        q3.copyTo(q0);
        tmp.copyTo(q3);
        q1.copyTo(tmp);
        q2.copyTo(q1);
        tmp.copyTo(q2);
        
        // 8.标准化
        mag.convertTo(mag, CvType.CV_8UC1);
        Core.normalize(mag, mag, 0, 255, Core.NORM_MINMAX, CvType.CV_8UC1);

        // 9.输出图片
        
        HighGui.imshow("原图-tiger.jpg", img);
        HighGui.imshow("离散傅里叶变换频谱图", mag);
        HighGui.moveWindow("原图-tiger.jpg", 400, 300);
        HighGui.moveWindow("离散傅里叶变换频谱图",1000, 300);
        HighGui.waitKey();
        System.exit(-1);
    }


    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("版本：" + Core.VERSION);

        final String NAME = "tiger.jpg";
        String path = new File(ClassLoader.getSystemResource(NAME).getPath()).getAbsolutePath();
        Mat image = Imgcodecs.imread(path);
        getDftImage(image);
    }
}
