/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpro;

import java.awt.Color;

import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.net.URL;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author giladPe
 */
public class FinalPro {

    public static void main(String[] args) {

        double percentege = 0;
        int imagePixselsize = 0;
        String fileImage = "";
        Mat res = null;
        //     imageToGray

        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

//            File input = new File("C:/QuadPotroler/FinalPro/src/images/20151207_153915.jpg");
            fileImage = threshholding();
            URL url = FinalPro.class.getResource("/images/threshdold.jpg");
            File input = new File(url.getPath());
            //File input = new File("C:/QuadPotroler/FinalPro/src/images/threshdold.jpg");
            BufferedImage image = ImageIO.read(input);
            int w = image.getWidth();
            int h = image.getHeight();

            imagePixselsize = w + h;
            int[] dataBuffInt = image.getRGB(0, 0, w, h, null, 0, w);//function is not working

            Color c = new Color(dataBuffInt[100]);

            double r = (c.getRed());   // = (dataBuffInt[100] >> 16) & 0xFF
            double g = (c.getGreen()); // = (dataBuffInt[100] >> 8)  & 0xFF
            double b = (c.getBlue());  // = (dataBuffInt[100] >> 0)  & 0xFF
            double a = (c.getAlpha()); // = (dataBuffInt[100] >> 24) & 0xFF

            percentege = ((r + g + b + a) / 1020) * 100;
            System.out.println(percentege + "%");

        } catch (Exception e) {
            System.out.println("eror");
        }

    }

    public static String threshholding() {
        Mat destination = null;
        Mat source = null;
        String str = "";
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            source = Imgcodecs.imread("C:/QuadPotroler/FinalPro/src/images/20151207_153915.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
            destination = new Mat(source.rows(), source.cols(), source.type());
            destination = source;
            Imgproc.threshold(source, destination, 127, 255, Imgproc.THRESH_TOZERO);
            Imgcodecs.imwrite("C:/QuadPotroler/FinalPro/src/images/threshdold.jpg", destination);
            str = "C:/QuadPotroler/FinalPro/src/images/threshdold.jpg";
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return str;
    }
}
