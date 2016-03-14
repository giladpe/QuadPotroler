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
        //     imageToGray

        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//            File input = new File("C:/QuadPotroler/FinalPro/src/images/20151207_153915.jpg");
            fileImage = threshholding("C:/QuadPotroler/FinalPro/src/images/park.png");
            File input = new File(fileImage);

//            FileChooser fc = new FileChooser();
//            File input = fc.showOpenDialog(null);
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
        }

    }

    public static String threshholding(String path) {
        Mat destination = null;
        Mat source = null;
        String retString = "";
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            source = Imgcodecs.imread("C:/QuadPotroler/FinalPro/src/images/20151207_153915.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
            destination = new Mat(source.rows(), source.cols(), source.type());
            destination = source;
            Imgproc.threshold(source, destination, 127, 255, Imgproc.THRESH_TOZERO);
            Imgcodecs.imwrite("C:/QuadPotroler/FinalPro/src/images/threshdold_img.jpg", destination);
            retString = "C:/QuadPotroler/FinalPro/src/images/threshdold_img.jpg";
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

        return retString;
    }

    public static void imageToGray() {
        try {

            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            File input = new File("C:/QuadPotroler/FinalPro/src/images/20151207_153915.jpg");
            BufferedImage image = ImageIO.read(input);

            byte[] data = ((DataBufferByte) image.getRaster().
                    getDataBuffer()).getData();
            Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
            mat.put(0, 0, data);
            Mat mat1 = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);

            Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);
            byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
            mat1.get(0, 0, data1);
            BufferedImage image1 = new BufferedImage(mat1.cols(), mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
            image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
            File ouptut = new File("C:/QuadPotroler/FinalPro/src/images/grayscale.jpg");
            ImageIO.write(image1, "jpg", ouptut);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//        try {
//            File input = new File("digital_image_processing.jpg");
//            image = ImageIO.read(input);
//            width = image.getWidth();
//            height = image.getHeight();
//
//            for (int i = 0; i < height; i++) {
//
//                for (int j = 0; j < width; j++) {
//
//                    Color c = new Color(image.getRGB(j, i));
//                    int red = (int) (c.getRed() * 0.299);
//                    int green = (int) (c.getGreen() * 0.587);
//                    int blue = (int) (c.getBlue() * 0.114);
//                    Color newColor = new Color(red + green + blue,
//                            red + green + blue, red + green + blue);
//
//                    image.setRGB(j, i, newColor.getRGB());
//                }
//            }
//
//            File ouptut = new File("grayscale.jpg");
//            ImageIO.write(image, "jpg", ouptut);
//
//        } catch (Exception e) {
//        }
}

/**
 * @param args the command line arguments
 */
