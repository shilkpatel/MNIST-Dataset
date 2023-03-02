import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {

        System.out.println("Hello world!");
        double[][] dataset = MNIST_formatting.read_file();
        K_MEANS test = new K_MEANS(dataset,10);
        test.save_clusters();
        Representation oupu= new Representation(5);// I can present first 5 images from a set
        oupu.display_images(dataset);
        /*
        BufferedImage img = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);

        for(int i=0;i<28;i++)
        {
            for(int j=0;j<28;j++)
            {
                int index = (i*28)+j;
                img.setRGB(j,i,(int)(dataset[0][index]));
            }
        }
        JLabel testlabel = new JLabel(new ImageIcon(img));
        System.out.println(img);

        JFrame testframe = new JFrame();
        testframe.add(testlabel);
        testframe.setVisible(true);
        */





    }
    public static void displaydigit(double[] a)
    {
        for(int i=0;i<28;i++)
        {
            String out="";
            for(int j=0;j<28;j++) {
                int index = (i * 28) + j;

                if (a[index] > 10) {
                    out += "1";
                } else {
                    out += "0";
                }
            }
            System.out.println(out);
        }
    }



    public static double sum_values(double[] a)
    {
        double b=0;
        for(int i=0;i<a.length;i++)
        {
            b+=a[i];
        }
        return b;
    }


}