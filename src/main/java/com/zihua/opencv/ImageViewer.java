package com.zihua.opencv;

import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * @ClassName ImageViewer
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/23 19:54
 */
public class ImageViewer {
    
    private JLabel imageView;
    private Mat image;
    private String windowName;
    
    public ImageViewer(Mat image) {
        this.image = image;
    }
    
    public ImageViewer(Mat image, String windowName) {
        this.image = image;
        this.windowName = windowName;
    }
    
    public void initLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
    
    public JFrame createJFrame(String windowName, int width, int height) {
        JFrame frame = new JFrame(windowName);
        this.imageView = new JLabel();
        final JScrollPane imageScrollPane = new JScrollPane(imageView);
        imageScrollPane.setPreferredSize(new Dimension(width, height));
        frame.add(imageScrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }
    
    private Image coverImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] buffer = new byte[bufferSize];
        
        mat.get(0, 0, buffer);
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
    }
    
    public void show() {
        initLookAndFeel();
        Image image = coverImage(this.image);
        JFrame frame = createJFrame(windowName, this.image.width() + 3 , this.image.height() + 3);
        this.imageView.setIcon(new ImageIcon(image));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
